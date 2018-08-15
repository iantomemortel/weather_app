package com.example.ian.weatherapp;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ian.weatherapp.data.local.DataRepository;
import com.example.ian.weatherapp.data.local.db.AppDatabase;
import com.example.ian.weatherapp.entity.Weather;
import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

public class WeatherApplication extends Application {
    private WeatherApplicationComponent weatherApplicationComponent;
    private AppDatabase appDatabase;

    @Inject
    WeatherService weatherService;
    @Inject
    Gson gson;


    public static WeatherApplication get(Activity activity) {
        return (WeatherApplication) activity.getApplication();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //Build local database
        appDatabase = AppDatabase.getAppDatabase(getApplicationContext());

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

    public WeatherApplicationComponent weatherApplicationComponent() {
        return weatherApplicationComponent;
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

    public DataRepository getRepository() {
        return DataRepository.getDataRepository(getDatabase(), weatherService, gson);
    }

}
