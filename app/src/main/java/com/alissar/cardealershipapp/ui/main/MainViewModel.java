package com.alissar.cardealershipapp.ui.main;

import androidx.lifecycle.ViewModel;

import com.alissar.cardealershipapp.data.repository.CarRepository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private final CarRepository repository;

    @Inject
    public MainViewModel(CarRepository repository) {
        this.repository = repository;
    }

    // Use repository to get data
}