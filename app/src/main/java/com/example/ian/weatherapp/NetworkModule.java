package com.example.ian.weatherapp;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

@Module(includes = ContextModule.class)
public class NetworkModule {
    @WeatherApplicationScope
    @Provides
    public File file(@ApplicationContext Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @WeatherApplicationScope
    @Provides
    public Cache cache(File file) {
        return new Cache(file, 10 * 1000 * 1000);
    }

    @WeatherApplicationScope
    @Provides
    public OkHttpClient okHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }
}
