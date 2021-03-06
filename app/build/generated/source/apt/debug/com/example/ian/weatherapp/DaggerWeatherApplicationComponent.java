// Generated by Dagger (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import android.content.Context;
import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Provider;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class DaggerWeatherApplicationComponent implements WeatherApplicationComponent {
  private Provider<Context> contextProvider;

  private Provider<File> fileProvider;

  private Provider<Cache> cacheProvider;

  private Provider<OkHttpClient> okHttpClientProvider;

  private Provider<Retrofit> weatherRetrofitProvider;

  private Provider<WeatherService> weatherServiceProvider;

  private Provider<Gson> gsonProvider;

  private DaggerWeatherApplicationComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.contextProvider =
        DoubleCheck.provider(ContextModule_ContextFactory.create(builder.contextModule));
    this.fileProvider =
        DoubleCheck.provider(
            NetworkModule_FileFactory.create(builder.networkModule, contextProvider));
    this.cacheProvider =
        DoubleCheck.provider(
            NetworkModule_CacheFactory.create(builder.networkModule, fileProvider));
    this.okHttpClientProvider =
        DoubleCheck.provider(
            NetworkModule_OkHttpClientFactory.create(builder.networkModule, cacheProvider));
    this.weatherRetrofitProvider =
        DoubleCheck.provider(
            WeatherAppServiceModule_WeatherRetrofitFactory.create(
                builder.weatherAppServiceModule, okHttpClientProvider));
    this.weatherServiceProvider =
        DoubleCheck.provider(
            WeatherAppServiceModule_WeatherServiceFactory.create(
                builder.weatherAppServiceModule, weatherRetrofitProvider));
    this.gsonProvider =
        DoubleCheck.provider(
            WeatherAppServiceModule_GsonFactory.create(builder.weatherAppServiceModule));
  }

  @Override
  public WeatherService getWeatherService() {
    return weatherServiceProvider.get();
  }

  @Override
  public Gson getGson() {
    return gsonProvider.get();
  }

  public static final class Builder {
    private ContextModule contextModule;

    private NetworkModule networkModule;

    private WeatherAppServiceModule weatherAppServiceModule;

    private Builder() {}

    public WeatherApplicationComponent build() {
      if (contextModule == null) {
        throw new IllegalStateException(ContextModule.class.getCanonicalName() + " must be set");
      }
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      if (weatherAppServiceModule == null) {
        this.weatherAppServiceModule = new WeatherAppServiceModule();
      }
      return new DaggerWeatherApplicationComponent(this);
    }

    public Builder weatherAppServiceModule(WeatherAppServiceModule weatherAppServiceModule) {
      this.weatherAppServiceModule = Preconditions.checkNotNull(weatherAppServiceModule);
      return this;
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public Builder contextModule(ContextModule contextModule) {
      this.contextModule = Preconditions.checkNotNull(contextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder activityModule(ActivityModule activityModule) {
      Preconditions.checkNotNull(activityModule);
      return this;
    }
  }
}
