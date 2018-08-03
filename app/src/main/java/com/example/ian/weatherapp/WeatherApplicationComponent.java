package com.example.ian.weatherapp;

import android.arch.lifecycle.ViewModelProvider;

import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;

import dagger.Component;

@WeatherApplicationScope
@Component(modules = {WeatherAppServiceModule.class, ActivityModule.class})
public interface WeatherApplicationComponent {
    WeatherService getWeatherService();
    Gson getGson();
}
