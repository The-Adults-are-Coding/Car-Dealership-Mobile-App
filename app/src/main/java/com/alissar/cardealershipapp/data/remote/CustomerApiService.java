package com.alissar.cardealershipapp.data.remote;

import com.alissar.cardealershipapp.data.model.Car;
import com.alissar.cardealershipapp.data.model.PaginatedResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CustomerApiService {

    @GET("Customer/my-cars")
    Call<List<Car>> getHistory();
    @GET("Customer/Balance/{id}")
    Call<Double> getBalance(
            @Path("id") String customerId
    );
}