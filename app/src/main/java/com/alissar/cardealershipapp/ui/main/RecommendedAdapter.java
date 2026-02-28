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
        // 1. Safety Check: If list is empty, do nothing to prevent crashes
        if (recommendedList == null || recommendedList.isEmpty()) {
            return;
        }

        // 2. Modulo Logic: This creates the "Infinite Loop" effect.
        // Even if 'position' is 5000, this maps it back to a valid index (0 to listSize-1)
        int actualPosition = position % recommendedList.size();

        Car car = recommendedList.get(actualPosition);

        // 3. Bind Data to Views
        // Assuming your Car model has these getters
        holder.tvName.setText(car.getFullName());

        // Simple formatting for price (e.g., "$ 25000")
        holder.tvPrice.setText(car.getFormattedPrice());

        // 4. Set Image (Using a placeholder for now)
        holder.imgCar.setImageResource(R.drawable.ic_launcher_background);

        // 5. Click Listener to Open Details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CarDetailsActivity.class);
            // Pass the specific car object or ID to the next activity
            // Ensure your Car class implements Serializable or Parcelable
            intent.putExtra("car_data", car);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void updateData(List<Car> cars) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(cars.size());
        recommendedList.clear();
        recommendedList.addAll(cars);
        notifyDataSetChanged();
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