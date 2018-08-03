package com.example.ian.weatherapp;

import android.arch.lifecycle.ViewModelProvider;

import com.example.ian.weatherapp.network.WeatherService;
import com.example.ian.weatherapp.viewmodel.LocationListViewModel;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class WeatherAppServiceModule {
    @WeatherApplicationScope
    @Provides
    public WeatherService weatherService(Retrofit weatherRetrofit) {
        return weatherRetrofit.create(WeatherService.class);
    }

    @WeatherApplicationScope
    @Provides
    public Retrofit weatherRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .client(okHttpClient)
                .build();
    }

    @WeatherApplicationScope
    @Provides
    Gson gson() {
        return new Gson();
    }

    @WeatherApplicationScope
    @Provides
    public ViewModelProvider.Factory factory(WeatherService weatherService, Gson gson) {
        return new LocationListViewModel.Factory(weatherService, gson);
    }
}
