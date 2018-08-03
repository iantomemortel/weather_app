package com.example.ian.weatherapp.Home;

import com.example.ian.weatherapp.Fragments.MainScreenFragment;
import com.example.ian.weatherapp.viewmodel.LocationListViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule {
    private final MainScreenFragment mainScreenFragment;

//    private final LocationListViewModel locationListViewModel;



    public HomeFragmentModule(MainScreenFragment mainScreenFragment) {
        this.mainScreenFragment = mainScreenFragment;
    }

//    public HomeFragmentModule(LocationListViewModel locationListViewModel) {
//        this.locationListViewModel = locationListViewModel;
//    }

    @HomeFragmentScope
    @Provides
    public MainScreenFragment mainScreenFragment()
    {
        return mainScreenFragment;
    }

//    @HomeFragmentScope
//    @Provides LocationListViewModel locationListViewModel(){
//        return locationListViewModel;
//    }
}
