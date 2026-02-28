package com.alissar.cardealershipapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.ui.inventory.CarInventoryActivity;
import com.alissar.cardealershipapp.utils.adapters.CarAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private ViewPager2 viewPagerRecommended;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());
     private HomeViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // --- 1. Setup Circular Auto-Scrolling Ad Bar ---
        viewPagerRecommended = view.findViewById(R.id.viewPagerRecommended);

        List<Car> recommendedCars = new ArrayList<>();

        RecommendedAdapter recAdapter = new RecommendedAdapter(recommendedCars);
        viewPagerRecommended.setAdapter(recAdapter);


        // OPTIONAL: Add a PageTransformer for a cool animation effect


        // --- 2. Setup Available Cars (Normal Horizontal List) ---
        // Inside onCreateView...

        RecyclerView recyclerView = view.findViewById(R.id.recyclerCars);

// 1. Use VERTICAL layout (Default)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
// Note: We removed LinearLayoutManager.HORIZONTAL

        recyclerView.setLayoutManager(layoutManager);

// 2. Optimization for scrolling inside NestedScrollView
        recyclerView.setNestedScrollingEnabled(false);

// 3. Set Adapter as before
        List<Car> availableCars = new ArrayList<>();

        CarAdapter adapter = new CarAdapter(availableCars);
        recyclerView.setAdapter(adapter);

        // Inside HomeFragment.java -> onCreateView method

        Button btnShowAll = view.findViewById(R.id.btnShowAll);

        btnShowAll.setOnClickListener(v -> {
            // Navigate to InventoryActivity
            Intent intent = new Intent(getActivity(), CarInventoryActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getCarList().observe(getViewLifecycleOwner(), cars -> {
            if (cars != null) {
                System.out.println(cars.size());
                recAdapter.updateData(cars);

                // --- CRITICAL FIX: Set position AFTER data loads ---
                // We calculate the midpoint to allow infinite scrolling
                int midPoint = Integer.MAX_VALUE / 2;
                // Adjust so the first item shown is index 0 of your list
                int startPosition = midPoint - (midPoint % cars.size());
                viewPagerRecommended.setCurrentItem(startPosition, false);
                viewPagerRecommended.setPageTransformer((page, position) -> {
                    float scaleFactor = 0.85f + (1 - Math.abs(position)) * 0.15f;
                    page.setScaleY(0.85f + scaleFactor * 0.15f);
                });

                recAdapter.updateData(cars);
            }
        });
        viewModel.getFiveCarList().observe(getViewLifecycleOwner(), cars -> {
            if (cars != null) {
                adapter.updateData(cars);
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), message -> {
            if (message != null) {
                Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
                System.out.println(message);
            }
        });

        viewModel.getAdbanner();
        viewModel.getFiveCar();

        return view;
    }

    // --- Auto Scroll Logic ---

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            // Move to next item
            viewPagerRecommended.setCurrentItem(viewPagerRecommended.getCurrentItem() + 1, true);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // Register the callback when the app is open
        viewPagerRecommended.registerOnPageChangeCallback(sliderCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop the auto-scroll when the user leaves the screen
        sliderHandler.removeCallbacks(sliderRunnable);
        viewPagerRecommended.unregisterOnPageChangeCallback(sliderCallback);
    }

    private ViewPager2.OnPageChangeCallback sliderCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            // Whenever a page is selected, remove pending callbacks and post a new one for 3 seconds later
            sliderHandler.removeCallbacks(sliderRunnable);
            sliderHandler.postDelayed(sliderRunnable, 3000); // 3 Seconds Delay
        }
    };
}