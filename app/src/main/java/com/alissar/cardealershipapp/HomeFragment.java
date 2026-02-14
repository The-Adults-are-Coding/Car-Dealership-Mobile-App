package com.alissar.cardealershipapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPagerRecommended;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // --- 1. Setup Circular Auto-Scrolling Ad Bar ---
        viewPagerRecommended = view.findViewById(R.id.viewPagerRecommended);

        List<Car> recommendedCars = new ArrayList<>();
        recommendedCars.add(new Car("Ferrari 488", "$ 280,000", R.drawable.ic_launcher_background));
        recommendedCars.add(new Car("Lamborghini Huracan", "$ 310,000", R.drawable.ic_launcher_background));
        recommendedCars.add(new Car("Porsche 911", "$ 190,000", R.drawable.ic_launcher_background));

        RecommendedAdapter recAdapter = new RecommendedAdapter(recommendedCars);
        viewPagerRecommended.setAdapter(recAdapter);

        // Start in the middle so user can scroll left immediately
        // (Integer.MAX_VALUE / 2) adjusted to be the start of the list
        int midPoint = Integer.MAX_VALUE / 2;
        int startPosition = midPoint - (midPoint % recommendedCars.size());
        viewPagerRecommended.setCurrentItem(startPosition, false);

        // OPTIONAL: Add a PageTransformer for a cool animation effect
        viewPagerRecommended.setPageTransformer((page, position) -> {
            float scaleFactor = 0.85f + (1 - Math.abs(position)) * 0.15f;
            page.setScaleY(0.85f + scaleFactor * 0.15f);
        });

        // --- 2. Setup Available Cars (Normal Horizontal List) ---
        RecyclerView recyclerView = view.findViewById(R.id.recyclerCars);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<Car> availableCars = new ArrayList<>();
        availableCars.add(new Car("Honda Civic", "$ 25,000", R.drawable.ic_launcher_background));
        availableCars.add(new Car("Toyota Camry", "$ 28,000", R.drawable.ic_launcher_background));
        availableCars.add(new Car("Ford Mustang", "$ 55,000", R.drawable.ic_launcher_background));

        CarAdapter carAdapter = new CarAdapter(availableCars);
        recyclerView.setAdapter(carAdapter);

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