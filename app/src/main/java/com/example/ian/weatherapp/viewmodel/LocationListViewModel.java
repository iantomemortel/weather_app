package com.example.ian.weatherapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.ian.weatherapp.ApplicationContext;
import com.example.ian.weatherapp.Model.Lists;
import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.data.local.db.AppDatabase;
import com.example.ian.weatherapp.entity.Item;
import com.example.ian.weatherapp.entity.LocalLists;
import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationListViewModel extends AndroidViewModel {
    private WeatherService weatherService;
    private Gson gson;
    private Application application;
    public MutableLiveData<ArrayList<Item>> liveDataListLocation;
    public MutableLiveData<String> returnMessage;
    public MutableLiveData<String> returnDbMessage;

    LocationListViewModel(Application application, WeatherService weatherService, Gson gson) {
        super(application);

        this.weatherService = weatherService;
        this.gson = gson;
        this.application = application;
        if (liveDataListLocation == null) {
            liveDataListLocation = new MutableLiveData<>();
            returnMessage = new MutableLiveData<>();
            returnDbMessage = new MutableLiveData<>();
            loadLocationList();
        }
    }

    public void refresh() {
        loadLocationList();
    }


    private void loadLocationList() {
        liveDataListLocation.setValue(null);


        if (!AppDatabase.getAppDatabase(application.getApplicationContext()).locationDao().loadItems().isEmpty()) {
            List<Item> localList;
            localList = AppDatabase.getAppDatabase(application.getApplicationContext()).locationDao().loadItems();
            loadItemsFromLocalDb(localList);
        } else {
            loadItemsFromServer();
        }


    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final WeatherService weatherService;
        private final Gson gson;
        private final Application application;

        @Inject
        public Factory(Application application, @NonNull WeatherService weatherService, Gson gson) {
            this.application = application;
            this.weatherService = weatherService;
            this.gson = gson;
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LocationListViewModel(application, weatherService, gson);
        }
    }

    private void updateLocalDb(ArrayList<Item> items) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(application.getApplicationContext());
        appDatabase.locationDao().insert(items);
    }

    private void loadItemsFromServer() {
        String url = "http://api.openweathermap.org/data/2.5/group?id=4517009,4548393,5391959&units=metric&APPID=5bea56b1d8227d22befcfa6582bc9b7c";

        Call<ResponseBody> call = weatherService.downloadFile(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        LocalLists localLists = gson.fromJson(response.body().string(), LocalLists.class);
                        returnMessage.postValue("Loaded successfully!");
                        liveDataListLocation.postValue(localLists.getList());

                        updateLocalDb(localLists.getList());

                    } catch (Exception e) {
                        returnMessage.postValue("Nothing found!");
                    }
                } else {
                    returnMessage.postValue(response.errorBody().toString());
                }


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
                returnMessage.postValue("No internet connection!");
            }
        });
    }

    private void loadItemsFromLocalDb(List<Item> localList) {
        returnMessage.postValue("Loaded from the local database!");
        liveDataListLocation.postValue((ArrayList<Item>) localList);
    }


}
