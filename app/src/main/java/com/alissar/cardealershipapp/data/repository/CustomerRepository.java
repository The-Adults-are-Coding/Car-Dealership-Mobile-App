package com.alissar.cardealershipapp.data.repository;

import android.util.Log;

import com.alissar.cardealershipapp.data.model.PaginatedResponse;
import com.alissar.cardealershipapp.data.remote.CarApiService;
import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.data.remote.CustomerApiService;
import com.alissar.cardealershipapp.utils.SessionManager;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRepository {

    private final CustomerApiService apiService;

    // Hilt automatically injects the ApiService created in NetworkModule
    @Inject
    public CustomerRepository(CustomerApiService apiService) {
        this.apiService = apiService;
    }
    public interface DataCallback {
        void onSuccess(List<Car> newCars, boolean hasNext);
        void onError(String errorMessage);
    }

    // Your existing getCars logic remains exactly the same...

    public void getHistory(MutableLiveData<List<Car>> carsLiveData, MutableLiveData<String> errorLiveData) {
        apiService.getHistory().enqueue(new Callback<List<Car>>() {
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
    public void getBalance(MutableLiveData<Double> carsLiveData, MutableLiveData<String> errorLiveData,String id) {
        apiService.getBalance(id).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful() && response.body() != null) {
                    carsLiveData.postValue(response.body());
                } else {
                    errorLiveData.postValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                errorLiveData.postValue(t.getMessage());
            }
        });
    }



}