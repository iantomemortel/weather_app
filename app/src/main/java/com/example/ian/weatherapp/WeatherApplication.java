package com.example.ian.weatherapp;

import android.app.Activity;
import android.app.Application;

public class WeatherApplication extends Application {
    private WeatherApplicationComponent weatherApplicationComponent;

    public static WeatherApplication get(Activity activity){
        return (WeatherApplication) activity.getApplication();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        weatherApplicationComponent = DaggerWeatherApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public WeatherApplicationComponent weatherApplicationComponent(){
        return weatherApplicationComponent;
    }
}
