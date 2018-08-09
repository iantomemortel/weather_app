package com.example.ian.weatherapp.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;


public class Weather {
    @ColumnInfo(name = "weather_id")
    public String id;
    public String main;
    public String description;
    public String icon;
}
