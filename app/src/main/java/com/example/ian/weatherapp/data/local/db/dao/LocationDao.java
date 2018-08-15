package com.example.ian.weatherapp.data.local.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.ian.weatherapp.Model.Location;
import com.example.ian.weatherapp.data.WeatherTypeConverters;
import com.example.ian.weatherapp.entity.Item;

import java.util.ArrayList;
import java.util.List;

@Dao
@TypeConverters(WeatherTypeConverters.class)
public interface LocationDao {

    @Query("select * from Item")
    LiveData<List<Item>> loadItems();

    @Query("select * from Item where id = :id")
    LiveData<Item> getItem(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<Item>locations);

    @Update
    void updateItems(ArrayList<Item> locations);

}
