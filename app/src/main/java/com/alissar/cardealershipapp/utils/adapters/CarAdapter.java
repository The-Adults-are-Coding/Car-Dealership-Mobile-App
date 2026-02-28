package com.alissar.cardealershipapp.utils.adapters;

import android.content.Intent; // ADD THIS IMPORT
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.ui.purchase.CarDetailsActivity;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.tvName.setText(car.getFullName());
        holder.tvPrice.setText(car.getFormattedPrice());

        // --- ADD THIS CLICK LISTENER ---
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarDetailsActivity.class);
            intent.putExtra("car_data", car); // Passes the car object
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView imgCar;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCarName);
            tvPrice = itemView.findViewById(R.id.tvCarPrice);
            imgCar = itemView.findViewById(R.id.imgCar);
        }
    }

    public void updateData(List<Car> newCars) {
        this.carList = newCars;
        notifyDataSetChanged();
    }
}