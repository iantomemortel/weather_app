package com.example.ian.weatherapp;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class WeatherApplication extends Application {
    private WeatherApplicationComponent weatherApplicationComponent;

    public static WeatherApplication get(Activity activity){
        return (WeatherApplication) activity.getApplication();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        weatherApplicationComponent = DaggerWeatherApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public WeatherApplicationComponent weatherApplicationComponent(){
        return weatherApplicationComponent;
    }
}
