package com.alissar.cardealershipapp.ui.inventory;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.utils.adapters.CarAdapter;

import java.util.ArrayList;
import java.util.List;

public class CarInventoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_inventroy);
        RecyclerView recyclerView = findViewById(R.id.recyclerAllCars);
        Toolbar toolbar = findViewById(R.id.toolbarInventory);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



// 1. Use VERTICAL layout (Default)
        LinearLayoutManager layoutManager = new LinearLayoutManager(CarInventoryActivity.this);
// Note: We removed LinearLayoutManager.HORIZONTAL

        recyclerView.setLayoutManager(layoutManager);

        // Initialize and set the adapter for the RecyclerView
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Mercedes C-Class", "$ 42,000", R.drawable.ic_launcher_background));
        cars.add(new Car("Audi A4", "$ 39,500", R.drawable.ic_launcher_background));
        cars.add(new Car("Tesla Model 3", "$ 41,000", R.drawable.ic_launcher_background));
        cars.add(new Car("BMW 3 Series", "$ 44,000", R.drawable.ic_launcher_background));// Replace with your data source
        CarAdapter adapter = new CarAdapter(cars);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Closes this activity and returns to Home
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
