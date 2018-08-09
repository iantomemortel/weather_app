// Generated by Dagger (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public final class NetworkModule_OkHttpClientFactory implements Factory<OkHttpClient> {
  private final NetworkModule module;

  private final Provider<Cache> cacheProvider;

  public NetworkModule_OkHttpClientFactory(NetworkModule module, Provider<Cache> cacheProvider) {
    this.module = module;
    this.cacheProvider = cacheProvider;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.okHttpClient(cacheProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static NetworkModule_OkHttpClientFactory create(
      NetworkModule module, Provider<Cache> cacheProvider) {
    return new NetworkModule_OkHttpClientFactory(module, cacheProvider);
  }

  public static OkHttpClient proxyOkHttpClient(NetworkModule instance, Cache cache) {
    return Preconditions.checkNotNull(
        instance.okHttpClient(cache), "Cannot return null from a non-@Nullable @Provides method");
  }
}
