package com.example.ian.weatherapp.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.ian.weatherapp.BuildConfig;
import com.example.ian.weatherapp.data.local.db.AppDatabase;
import com.example.ian.weatherapp.entity.Item;
import com.example.ian.weatherapp.entity.LocalLists;
import com.example.ian.weatherapp.entity.System;
import com.example.ian.weatherapp.network.WeatherService;
import com.example.ian.weatherapp.thread.DefaultExecutorSupplier;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataRepository {

    private static DataRepository dataRepository;

    private final AppDatabase mAppDatabase;
    private MediatorLiveData<List<Item>> mObservable;

    private WeatherService weatherService;
    private Gson gson;

    private MutableLiveData<List<Item>> items;

    private DataRepository(final AppDatabase appDatabase, WeatherService weatherService, Gson gson) {
        mAppDatabase = appDatabase;
        this.weatherService = weatherService;
        this.gson = gson;

        mObservable = new MediatorLiveData<>();

        mObservable.addSource(mAppDatabase.locationDao().loadItems().getValue()==null? loadItemsFromServer() : appDatabase.locationDao().loadItems(), new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> list) {
                mObservable.postValue(list);
            }
        });
    }


    public LiveData<List<Item>> getItemList() {
        return mObservable;
    }

    public LiveData<Item> getItem(String id) {
        return mAppDatabase.locationDao().getItem(id);
    }

    public static DataRepository getDataRepository(final AppDatabase appDatabase, WeatherService weatherService, Gson gson) {

        if (dataRepository == null) {
            synchronized (DataRepository.class) {
                if (dataRepository == null) {
                    dataRepository = new DataRepository(appDatabase, weatherService, gson);
                }
            }
        }
        return dataRepository;
    }

    private LiveData<List<Item>> loadItemsFromServer() {
        String openWeatherApiKey = BuildConfig.openWeatherApiKey;
        String url = "http://api.openweathermap.org/data/2.5/group?id=4517009,4548393,5391959&units=metric&APPID=" + openWeatherApiKey;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .build();

        weatherService = retrofit.create(WeatherService.class);

        gson = new Gson();

        items = new MediatorLiveData<>();
        items.setValue(null);

        Call<ResponseBody> call = weatherService.downloadFile(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        java.lang.System.out.print("fetched from server");
                        Log.i("DataRepository", "load from server");
                        String valueJson = response.body().string();
                        LocalLists localLists = gson.fromJson(valueJson, LocalLists.class);
//                        returnMessage.postValue("Successfully loaded from server!");
//                        liveDataListLocation.postValue(localLists.getList());
                        updateLocalDb(localLists.getList());

                        items.postValue(localLists.getList());
//                        writeToSD(); //save the db locally for testing purposes

//                        return localLists.getList();

                    } catch (Exception e) {
                        java.lang.System.out.print(e.getMessage());
                    }
                } else {
//                    returnMessage.postValue(response.errorBody().toString());
                }


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
//                returnMessage.postValue("No internet connection!");
            }
        });

        return items;
    }

    private void updateLocalDb(final List<Item> items) {

        DefaultExecutorSupplier.getInstance().forBackgroundTasks()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        // do some background work here.

                        mAppDatabase.locationDao().insert(items);
                    }
                });



    }

}
