// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import com.example.ian.weatherapp.network.WeatherService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class WeatherAppServiceModule_WeatherServiceFactory
    implements Factory<WeatherService> {
  private final WeatherAppServiceModule module;

  private final Provider<Retrofit> weatherRetrofitProvider;

  public WeatherAppServiceModule_WeatherServiceFactory(
      WeatherAppServiceModule module, Provider<Retrofit> weatherRetrofitProvider) {
    assert module != null;
    this.module = module;
    assert weatherRetrofitProvider != null;
    this.weatherRetrofitProvider = weatherRetrofitProvider;
  }

  @Override
  public WeatherService get() {
    return Preconditions.checkNotNull(
        module.weatherService(weatherRetrofitProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<WeatherService> create(
      WeatherAppServiceModule module, Provider<Retrofit> weatherRetrofitProvider) {
    return new WeatherAppServiceModule_WeatherServiceFactory(module, weatherRetrofitProvider);
  }
}
