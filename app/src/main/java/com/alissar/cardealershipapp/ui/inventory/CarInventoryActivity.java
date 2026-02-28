package com.alissar.cardealershipapp.ui.inventory;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.utils.adapters.CarAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint; // Import this

// --- HILT CHANGE: Must have this annotation ---
@AndroidEntryPoint
public class CarInventoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CarAdapter adapter;
    private InventoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_inventroy); // Or your main layout

        recyclerView = findViewById(R.id.recyclerAllCars);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // This line caused the crash before.
        // Now that @AndroidEntryPoint and @HiltViewModel are added, it will work.
        viewModel = new ViewModelProvider(this).get(InventoryViewModel.class);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    System.out.println("dx: " + dx +" dy: " + dy + ", isLoading: " + viewModel.isLoading);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager != null) {
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                        // --- DEBUG LOGS ---
                        // Look at this in Logcat!
                        System.out.println("SCROLL_DEBUG: dy=" + dy +
                                " | visible=" + visibleItemCount +
                                " | first=" + firstVisibleItemPosition +
                                " | total=" + totalItemCount +
                                " | isLoading=" + viewModel.isLoading);
                        // Check if we are at the bottom
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                ) { // Only load if we have enough items to scroll

                            // Ask ViewModel for more
                            viewModel.loadNextPage();
                        }
                    }
                }
            }
        });

        // 2. Observe Data
        viewModel.getCarList().observe(this, cars -> {
            if (cars != null) {
                adapter.addData(cars);
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {
            if (message != null) {
                Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.loadNextPage();
    }
}