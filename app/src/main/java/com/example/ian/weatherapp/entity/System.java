package com.example.ian.weatherapp.entity;

import android.arch.persistence.room.Entity;


@Entity
public class System {
    public String type;
    public String message;
    public String country;
    public String sunrise;
    public String sunset;
}
