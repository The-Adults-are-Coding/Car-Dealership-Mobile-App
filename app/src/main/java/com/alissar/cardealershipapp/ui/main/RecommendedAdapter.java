package com.alissar.cardealershipapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Car;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecViewHolder> {

    private List<Car> recommendedList;

    public RecommendedAdapter(List<Car> recommendedList) {
        this.recommendedList = recommendedList;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommanded, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        // Modulo logic: If position is 100 and list size is 3, it fetches item at index 1
        int actualPosition = position % recommendedList.size();

        Car car = recommendedList.get(actualPosition);
        holder.tvName.setText(car.getName());
        holder.tvPrice.setText(car.getPrice());
        holder.imgCar.setImageResource(car.getImageResId());
    }

    @Override
    public int getItemCount() {
        // Return a huge number so the user can scroll "forever"
        return Integer.MAX_VALUE;
    }

    static class RecViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView imgCar;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRecCarName);
            tvPrice = itemView.findViewById(R.id.tvRecCarPrice);
            imgCar = itemView.findViewById(R.id.imgRecCar);
        }
    }
}