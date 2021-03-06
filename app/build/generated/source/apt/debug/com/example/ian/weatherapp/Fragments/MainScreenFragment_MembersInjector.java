// Generated by Dagger (https://google.github.io/dagger).
package com.example.ian.weatherapp.Fragments;

import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MainScreenFragment_MembersInjector
    implements MembersInjector<MainScreenFragment> {
  private final Provider<WeatherService> weatherServiceProvider;

  private final Provider<Gson> gsonProvider;

  public MainScreenFragment_MembersInjector(
      Provider<WeatherService> weatherServiceProvider, Provider<Gson> gsonProvider) {
    this.weatherServiceProvider = weatherServiceProvider;
    this.gsonProvider = gsonProvider;
  }

  public static MembersInjector<MainScreenFragment> create(
      Provider<WeatherService> weatherServiceProvider, Provider<Gson> gsonProvider) {
    return new MainScreenFragment_MembersInjector(weatherServiceProvider, gsonProvider);
  }

  @Override
  public void injectMembers(MainScreenFragment instance) {
    injectWeatherService(instance, weatherServiceProvider.get());
    injectGson(instance, gsonProvider.get());
  }

  public static void injectWeatherService(
      MainScreenFragment instance, WeatherService weatherService) {
    instance.weatherService = weatherService;
  }

  public static void injectGson(MainScreenFragment instance, Gson gson) {
    instance.gson = gson;
  }
}
