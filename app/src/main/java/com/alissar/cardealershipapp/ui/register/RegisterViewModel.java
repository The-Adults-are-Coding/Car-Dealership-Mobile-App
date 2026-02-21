package com.alissar.cardealershipapp.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.alissar.cardealershipapp.data.model.AuthResponse;
import com.alissar.cardealershipapp.data.model.RegisterRequest;
import com.alissar.cardealershipapp.data.repository.AuthRepository;
import com.alissar.cardealershipapp.utils.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends ViewModel {
    private final AuthRepository repository;

    @Inject
    public RegisterViewModel(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<AuthResponse>> register(String nationalId, String address, String occupation, String firstName, String lastName,
                                                     String email, String password, String phone) {
        RegisterRequest request = new RegisterRequest(firstName,lastName, email, password, phone, nationalId, address, occupation);
        return repository.registerUser(request);
    }
}