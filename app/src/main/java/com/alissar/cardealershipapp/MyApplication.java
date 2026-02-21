package com.alissar.cardealershipapp;

import android.app.Application;

import com.alissar.cardealershipapp.di.AppModule;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp  // <--- THIS MUST BE HERE

public class MyApplication extends Application {

    // This is the single instance of your Dependency Container
    public AppModule appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppModule();
    }
}