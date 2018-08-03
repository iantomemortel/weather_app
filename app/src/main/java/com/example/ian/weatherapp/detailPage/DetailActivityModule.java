package com.example.ian.weatherapp.detailPage;

import com.example.ian.weatherapp.Activities.WeatherDetailActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailActivityModule {
    WeatherDetailActivity weatherDetailActivity;

    public DetailActivityModule(WeatherDetailActivity weatherDetailActivity) {
        this.weatherDetailActivity = weatherDetailActivity;
    }
    @DetailActivityScope
    @Provides
    public WeatherDetailActivity getWeatherDetailActivity() {
        return weatherDetailActivity;
    }
}
