package com.example.ian.weatherapp.detailPage;

import com.example.ian.weatherapp.Activities.WeatherDetailActivity;
import com.example.ian.weatherapp.WeatherApplicationComponent;

import dagger.Component;
@DetailActivityScope
@Component(modules = DetailActivityModule.class, dependencies = WeatherApplicationComponent.class)
public interface DetailActivityComponent {
    void injectDetailActivity(WeatherDetailActivity weatherDetailActivity);
}
