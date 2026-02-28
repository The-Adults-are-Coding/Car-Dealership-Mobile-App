package com.alissar.cardealershipapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alissar.cardealershipapp.R;
import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.utils.adapters.CarAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate the layout
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // 2. Setup RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Mock Data (Cars the user has "bought")
        List<Car> soldCars = new ArrayList<>();

        // 4. Set Adapter
        CarAdapter adapter = new CarAdapter(soldCars);
        recyclerView.setAdapter(adapter);

        return view;
    }
}