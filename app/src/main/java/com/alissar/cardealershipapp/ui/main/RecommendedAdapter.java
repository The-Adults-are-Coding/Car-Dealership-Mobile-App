// File: app/src/main/java/com/alissar/cardealershipapp/ui/main/RecommendedAdapter.java
package com.alissar.cardealershipapp.ui.main;

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

    }

    @Override
    public int getItemCount() {
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