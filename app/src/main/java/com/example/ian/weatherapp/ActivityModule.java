package com.example.ian.weatherapp;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity context;


    public ActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @WeatherApplicationScope
    @Named("activity_context")
    public Context context()
    {
        return context;
    }
}
