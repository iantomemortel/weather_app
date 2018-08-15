package com.example.ian.weatherapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ian.weatherapp.ApplicationContext;
import com.example.ian.weatherapp.BuildConfig;
import com.example.ian.weatherapp.Model.Lists;
import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.WeatherApplication;
import com.example.ian.weatherapp.data.local.db.AppDatabase;
import com.example.ian.weatherapp.entity.Item;
import com.example.ian.weatherapp.entity.LocalLists;
import com.example.ian.weatherapp.network.WeatherService;
import com.example.ian.weatherapp.thread.DefaultExecutorSupplier;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationListViewModel extends AndroidViewModel {
    private WeatherService weatherService;
    private Gson gson;
    private Application application;

    public LiveData<List<Item>> getLiveDataListLocation() {
        return liveDataListLocation;
    }

    private final MediatorLiveData<List<Item>> liveDataListLocation;
//    public MutableLiveData<String> returnMessage;
//    public MutableLiveData<String> returnDbMessage;

    LocationListViewModel(Application application, WeatherService weatherService, Gson gson) {
        super(application);

        this.weatherService = weatherService;
        this.gson = gson;
        this.application = application;
//        if (liveDataListLocation == null) {
//            liveDataListLocation = new MutableLiveData<>();
//            returnMessage = new MutableLiveData<>();
//            returnDbMessage = new MutableLiveData<>();
//            loadLocationList();
//        }


        liveDataListLocation = new MediatorLiveData<>();
        liveDataListLocation.setValue(null);

        LiveData<List<Item>> items = ((WeatherApplication) application).getRepository().getItemList();

        liveDataListLocation.addSource(items, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> list) {
                liveDataListLocation.postValue(list);
            }
        });
    }

    public void refresh() {
//        loadItemsFromServer();
    }


//    private void loadLocationList() {
//        liveDataListLocation.setValue(null);
//
//        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(new Runnable() {
//            @Override
//            public void run() {
//                if (!AppDatabase.getAppDatabase(application.getApplicationContext()).locationDao().loadItems().isEmpty()) {
//                    loadItemsFromLocalDb();
//                } else {
////                    loadItemsFromServer();
//                }
//            }
//        });
//
//
//    }

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





    private void writeToSD() throws IOException {
        File sd = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), "Back-up DB");
        String DB_PATH;

        DB_PATH = application.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator;

        if (sd.canWrite()) {
            String currentDBPath = "weather.db";
            String backupDBPath = "backupweather.db";
            File currentDB = new File(DB_PATH, currentDBPath);
            File backupDB = new File(sd, backupDBPath);

            FileChannel src = new FileInputStream(currentDB).getChannel();
            FileChannel dst = new FileOutputStream(backupDB).getChannel();
            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();

        }
    }


//    private void loadItemsFromLocalDb(){
//        LiveData<List<Item>> localList;
//        localList = AppDatabase.getAppDatabase(application.getApplicationContext()).locationDao().loadItems();
//        returnMessage.postValue("Loaded from the local database!");
//        liveDataListLocation.postValue((List<Item>) localList);
//    }


}
