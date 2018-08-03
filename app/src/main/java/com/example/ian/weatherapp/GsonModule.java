package com.example.ian.weatherapp;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {
    @WeatherApplicationScope
    @Provides
    Gson gson(){
        return new Gson();
    }
}
