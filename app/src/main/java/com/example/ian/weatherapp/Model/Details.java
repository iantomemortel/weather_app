package com.example.ian.weatherapp.Model;

import android.support.annotation.Nullable;

import java.io.Serializable;

public class Details{
    String key;
    @Nullable
    String value;

    public Details() {
    }

    public Details(String key,@Nullable String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Nullable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    @Override
//    public int compareTo(Details details) {
//        String compareQuantity = ((Details)details).getValue();
//        if(this.value.compareToIgnoreCase())
//    }
}
