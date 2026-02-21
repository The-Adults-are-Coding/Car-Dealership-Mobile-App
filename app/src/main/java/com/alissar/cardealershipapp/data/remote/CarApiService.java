package com.alissar.cardealershipapp.data.remote;

import com.alissar.cardealershipapp.data.model.Car;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarApiService {
    @GET("cars") // Endpoint: https://your-api.com/cars
    Call<List<Car>> getCars();
}