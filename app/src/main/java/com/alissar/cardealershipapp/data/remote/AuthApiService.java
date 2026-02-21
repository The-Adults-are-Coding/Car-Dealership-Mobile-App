package com.alissar.cardealershipapp.data.remote;

import com.alissar.cardealershipapp.data.model.AuthResponse;
import com.alissar.cardealershipapp.data.model.LoginRequest;
import com.alissar.cardealershipapp.data.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiService {
    @POST("Auth/login")
    Call<AuthResponse> login(@Body LoginRequest request);

    @POST("Auth/register")
    Call<AuthResponse> register(@Body RegisterRequest request);
}