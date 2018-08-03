package com.example.ian.weatherapp;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }
    @ApplicationContext
    @WeatherApplicationScope
    @Provides
    public Context context(){
        return context;
    }
}
