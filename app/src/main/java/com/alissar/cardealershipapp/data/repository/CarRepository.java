package com.alissar.cardealershipapp.data.repository;

import android.util.Log;

import com.alissar.cardealershipapp.data.model.PaginatedResponse;
import com.alissar.cardealershipapp.data.remote.CarApiService;
import com.alissar.cardealershipapp.data.model.Car;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarRepository {

    private final CarApiService apiService;

    // Hilt automatically injects the ApiService created in NetworkModule
    @Inject
    public CarRepository(CarApiService apiService) {
        this.apiService = apiService;
    }
    public interface DataCallback {
        void onSuccess(List<Car> newCars, boolean hasNext);
        void onError(String errorMessage);
    }

    // Your existing getCars logic remains exactly the same...
    public void getCars(int page, int size, DataCallback callback) {

        apiService.getAllCars(page, size).enqueue(new Callback<PaginatedResponse<Car>>() {
            @Override
            public void onResponse(Call<PaginatedResponse<Car>> call, Response<PaginatedResponse<Car>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Success: Pass data back to ViewModel
                    callback.onSuccess(
                            response.body().getItems(),
                            response.body().hasNext()
                    );
                } else {
                    // Error: Pass error message
                    callback.onError("Error Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PaginatedResponse<Car>> call, Throwable t) {
                // Failure: Pass network error
                callback.onError(t.getMessage());
            }
        });
    }
    public void getAdbanner(MutableLiveData<List<Car>> carsLiveData, MutableLiveData<String> errorLiveData) {
        apiService.getAdbanner().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    carsLiveData.postValue(response.body());
                } else {
                    errorLiveData.postValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                errorLiveData.postValue(t.getMessage());
            }
        });
    }
    public void getFiveCar(MutableLiveData<List<Car>> carsLiveData, MutableLiveData<String> errorLiveData) {
        apiService.getFiveCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    carsLiveData.postValue(response.body());
                } else {
                    errorLiveData.postValue("Error: " + response.code());
                    Log.println(Log.ERROR,"",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Log.println(Log.ERROR,"++++++++++++++++", Objects.requireNonNull(t.getMessage()));
                errorLiveData.postValue(t.getMessage());
            }
        });
    }
}