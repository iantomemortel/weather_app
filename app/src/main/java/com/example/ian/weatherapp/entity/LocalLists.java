package com.example.ian.weatherapp.entity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.io.Serializable;
import java.util.List;

public class LocalLists implements Serializable {
    private List<Item> list;
    public LocalLists() {
    }
    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

}
