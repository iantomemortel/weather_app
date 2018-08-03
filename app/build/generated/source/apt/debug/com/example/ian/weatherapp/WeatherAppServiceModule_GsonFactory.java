// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class WeatherAppServiceModule_GsonFactory implements Factory<Gson> {
  private final WeatherAppServiceModule module;

  public WeatherAppServiceModule_GsonFactory(WeatherAppServiceModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Gson get() {
    return Preconditions.checkNotNull(
        module.gson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Gson> create(WeatherAppServiceModule module) {
    return new WeatherAppServiceModule_GsonFactory(module);
  }
}