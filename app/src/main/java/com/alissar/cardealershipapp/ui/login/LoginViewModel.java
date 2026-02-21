package com.alissar.cardealershipapp.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alissar.cardealershipapp.data.model.AuthResponse;
import com.alissar.cardealershipapp.data.repository.AuthRepository;
import com.alissar.cardealershipapp.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {
    private final AuthRepository repository;

    @Inject
    public LoginViewModel(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<AuthResponse>> login(String email, String password) {
        return repository.loginUser(email, password);
    }
}