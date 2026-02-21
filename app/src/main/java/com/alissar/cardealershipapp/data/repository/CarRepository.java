package com.alissar.cardealershipapp.data.repository;

import com.alissar.cardealershipapp.data.remote.CarApiService;

import javax.inject.Inject;

public class CarRepository {
    CarApiService apiService;
    @Inject
    public CarRepository(CarApiService apiService) {
        this.apiService = apiService;
    }

}
