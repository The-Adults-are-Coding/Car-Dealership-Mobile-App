package com.alissar.cardealershipapp.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alissar.cardealershipapp.data.model.AuthResponse;
import com.alissar.cardealershipapp.data.model.LoginRequest;
import com.alissar.cardealershipapp.data.model.RegisterRequest;
import com.alissar.cardealershipapp.data.remote.AuthApiService;
import com.alissar.cardealershipapp.utils.Resource;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthApiService apiService;

    @Inject
    public AuthRepository(AuthApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<Resource<AuthResponse>> loginUser(String email, String password) {
        MutableLiveData<Resource<AuthResponse>> loginData = new MutableLiveData<>();
        loginData.setValue(Resource.loading());

        apiService.login(new LoginRequest(email, password)).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginData.setValue(Resource.success(response.body()));
                } else {
                    loginData.setValue(Resource.error("Invalid Credentials"));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                loginData.setValue(Resource.error("Network connection failed"));
            }
        });
        return loginData;
    }
    public LiveData<Resource<AuthResponse>> registerUser(RegisterRequest request) {
        MutableLiveData<Resource<AuthResponse>> registerData = new MutableLiveData<>();
        registerData.setValue(Resource.loading());

        apiService.register(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    registerData.setValue(Resource.success(response.body()));
                } else {
                    // Tip: You can parse the error body from the server for a specific message
                    registerData.setValue(Resource.error("Registration failed. Email might already exist."));
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                registerData.setValue(Resource.error("No internet connection."));
            }
        });
        return registerData;
    }
}
