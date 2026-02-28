package com.alissar.cardealershipapp.data.remote;

import com.alissar.cardealershipapp.data.model.Car;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CarApiService {

    // The relative path from the Base URL
    @GET("Car/getAllCars")
    Call<List<Car>> getAllCars();
    @GET("Car/getAdbanner")
    Call<List<Car>> getAdbanner();

}