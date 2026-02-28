package com.alissar.cardealershipapp.data.remote;

import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.data.model.PaginatedResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarApiService {
    @GET("cars") // Endpoint: https://your-api.com/cars
    Call<List<Car>> getCars();
    @GET("fiveCars")
    Call<List<Car>> getFiveCars();

    // The relative path from the Base URL
    @GET("Car/getAllCars")
    Call<PaginatedResponse<Car>> getAllCars(
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );
    @GET("Car/getAdbanner")
    Call<List<Car>> getAdbanner();

}