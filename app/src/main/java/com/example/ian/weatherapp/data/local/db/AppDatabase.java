package com.example.ian.weatherapp.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ian.weatherapp.data.local.db.dao.LocationDao;
import com.example.ian.weatherapp.entity.Item;

@Database(entities = Item.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public abstract LocationDao locationDao();

    public static AppDatabase getAppDatabase(Context context){
        if(appDatabase==null)
        {

            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "weather").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

}
