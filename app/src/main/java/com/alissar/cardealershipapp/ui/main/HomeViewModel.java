package com.alissar.cardealershipapp.ui.main;

 // Check your package

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.data.repository.CarRepository;

import java.util.List;

import javax.inject.Inject; // Import this
import dagger.hilt.android.lifecycle.HiltViewModel; // Import this

// --- HILT CHANGE 1: Add Annotation ---
@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final CarRepository repository;
    private final MutableLiveData<List<Car>> carList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    // --- HILT CHANGE 2: Add @Inject to constructor ---
    @Inject
    public HomeViewModel(CarRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Car>> getCarList() {
        return carList;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void getAdbanner() {
        repository.getAdbanner(carList, errorMessage);
    }
}