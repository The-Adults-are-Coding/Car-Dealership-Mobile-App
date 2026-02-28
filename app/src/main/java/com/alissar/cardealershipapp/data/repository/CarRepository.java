package com.alissar.cardealershipapp.data.repository;

import com.alissar.cardealershipapp.data.remote.CarApiService;
import com.alissar.cardealershipapp.data.model.Car;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
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

    // Your existing getCars logic remains exactly the same...
    public void getCars(MutableLiveData<List<Car>> carsLiveData, MutableLiveData<String> errorLiveData) {
        apiService.getAllCars().enqueue(new Callback<List<Car>>() {
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
    public void getAdbanner(MutableLiveData<List<Car>> carsLiveData, MutableLiveData<String> errorLiveData) {
        apiService.getAllCars().enqueue(new Callback<List<Car>>() {
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
}