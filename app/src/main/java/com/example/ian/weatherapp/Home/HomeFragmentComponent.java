package com.example.ian.weatherapp.Home;

import com.example.ian.weatherapp.Fragments.MainScreenFragment;
import com.example.ian.weatherapp.WeatherApplicationComponent;
import com.example.ian.weatherapp.viewmodel.LocationListViewModel;

import dagger.Component;
@HomeFragmentScope
@Component(modules = HomeFragmentModule.class, dependencies = WeatherApplicationComponent.class)
public interface HomeFragmentComponent {
    void inject(MainScreenFragment mainScreenFragment);
}
