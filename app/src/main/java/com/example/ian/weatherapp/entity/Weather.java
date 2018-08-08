package com.example.ian.weatherapp.entity;

import android.arch.persistence.room.Entity;

@Entity
public class Weather {
    public String id;
    public String main;
    public String description;
    public String icon;
}
