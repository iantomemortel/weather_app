// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import android.arch.lifecycle.ViewModelProvider;
import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class WeatherAppServiceModule_FactoryFactory
    implements Factory<ViewModelProvider.Factory> {
  private final WeatherAppServiceModule module;

  private final Provider<WeatherService> weatherServiceProvider;

  private final Provider<Gson> gsonProvider;

  public WeatherAppServiceModule_FactoryFactory(
      WeatherAppServiceModule module,
      Provider<WeatherService> weatherServiceProvider,
      Provider<Gson> gsonProvider) {
    assert module != null;
    this.module = module;
    assert weatherServiceProvider != null;
    this.weatherServiceProvider = weatherServiceProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public ViewModelProvider.Factory get() {
    return Preconditions.checkNotNull(
        module.factory(weatherServiceProvider.get(), gsonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ViewModelProvider.Factory> create(
      WeatherAppServiceModule module,
      Provider<WeatherService> weatherServiceProvider,
      Provider<Gson> gsonProvider) {
    return new WeatherAppServiceModule_FactoryFactory(module, weatherServiceProvider, gsonProvider);
  }
}
