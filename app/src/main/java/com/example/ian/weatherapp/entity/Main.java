package com.example.ian.weatherapp.entity;

import android.arch.persistence.room.Entity;


@Entity
public class Main {
    public String temp;
    public String pressure;
    public String humidity;
    public String temp_min;
    public String temp_max;

}
