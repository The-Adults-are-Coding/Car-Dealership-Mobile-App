package com.alissar.cardealershipapp.ui.inventory; // Check your package

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.data.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject; // Import this
import dagger.hilt.android.lifecycle.HiltViewModel; // Import this

// --- HILT CHANGE 1: Add Annotation ---
@HiltViewModel
public class InventoryViewModel extends ViewModel {

    private final CarRepository repository;

    // LiveData for UI
    private final MutableLiveData<List<Car>> carList = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> hasNextPage = new MutableLiveData<>();

    // Pagination State
    private int currentPage = 1;
    private final int pageSize = 3;
    private boolean isLastPage = false;
    public boolean isLoading = false;

    // Helper list to accumulate data
    private final List<Car> currentLoadedCars = new ArrayList<>();

    @Inject
    public InventoryViewModel(CarRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Car>> getCarList() { return carList; }
    public LiveData<String> getErrorMessage() { return errorMessage; }

    public void loadNextPage() {
        if (isLoading || isLastPage) return;

        isLoading = true; // LOCK

        // We pass the callback logic to the repository
        repository.getCars(currentPage, pageSize, new CarRepository.DataCallback() {
            @Override
            public void onSuccess(List<Car> newCars, boolean hasNext) {
                isLoading = false; // UNLOCK

                if (newCars != null) {
                    currentLoadedCars.addAll(newCars);
                    carList.postValue(currentLoadedCars); // Update UI

                    if (!hasNext) {
                        isLastPage = true;
                    } else {
                        currentPage++;
                    }
                }
            }

            @Override
            public void onError(String message) {
                isLoading = false; // UNLOCK (Crucial!)
                errorMessage.postValue(message);
            }
        });
    }

    // Call this to reset (e.g., Pull to Refresh)
    public void refresh() {
        currentPage = 1;
        isLastPage = false;
        currentLoadedCars.clear();
        loadNextPage();
    }
}