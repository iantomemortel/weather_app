// Generated by Dagger (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;
import okhttp3.Cache;

public final class NetworkModule_CacheFactory implements Factory<Cache> {
  private final NetworkModule module;

  private final Provider<File> fileProvider;

  public NetworkModule_CacheFactory(NetworkModule module, Provider<File> fileProvider) {
    this.module = module;
    this.fileProvider = fileProvider;
  }

  @Override
  public Cache get() {
    return Preconditions.checkNotNull(
        module.cache(fileProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static NetworkModule_CacheFactory create(
      NetworkModule module, Provider<File> fileProvider) {
    return new NetworkModule_CacheFactory(module, fileProvider);
  }

  public static Cache proxyCache(NetworkModule instance, File file) {
    return Preconditions.checkNotNull(
        instance.cache(file), "Cannot return null from a non-@Nullable @Provides method");
  }
}
