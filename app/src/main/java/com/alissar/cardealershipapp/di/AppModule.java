package com.alissar.cardealershipapp.di;

import com.alissar.cardealershipapp.data.remote.AuthApiService;
import com.alissar.cardealershipapp.data.remote.CarApiService;
import com.alissar.cardealershipapp.data.repository.AuthRepository;
import com.alissar.cardealershipapp.data.repository.CarRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static CarRepository provideCarRepository(CarApiService apiService) {
        return new CarRepository(apiService);
    }

    @Provides
    @Singleton
    public static AuthRepository provideAuthRepository(AuthApiService apiService) {
        return new AuthRepository(apiService);
    }
}