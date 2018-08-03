package com.example.ian.weatherapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ian.weatherapp.Model.Lists;
import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.network.WeatherService;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationListViewModel extends ViewModel {
    private WeatherService weatherService;
    private Gson gson;
    public MutableLiveData<ArrayList<Location>> liveDataListLocation;

    LocationListViewModel(WeatherService weatherService, Gson gson) {
        this.weatherService = weatherService;
        this.gson = gson;
        if (liveDataListLocation == null) {
            liveDataListLocation = new MutableLiveData<>();
            loadLocationList();
        }
    }

    public void refresh() {
        loadLocationList();
    }


    private void loadLocationList() {
        liveDataListLocation.setValue(null);
        String url = "http://api.openweathermap.org/data/2.5/group?id=4517009,4548393,5391959&units=metric&APPID=5bea56b1d8227d22befcfa6582bc9b7c";

        Call<ResponseBody> call = weatherService.downloadFile(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    if (response != null) {
                        Log.d("onResponse", "Response came from server");

                        Lists list = gson.fromJson(response.body().string(), Lists.class);

                        liveDataListLocation.postValue(list.getList());
                    }

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
            }
        });

    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final WeatherService weatherService;
        private final Gson gson;

        @Inject
        public Factory(@NonNull WeatherService weatherService, Gson gson) {
            this.weatherService = weatherService;
            this.gson = gson;
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LocationListViewModel(weatherService, gson);
        }
    }


}
