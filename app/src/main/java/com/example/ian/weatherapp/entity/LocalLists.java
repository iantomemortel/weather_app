package com.example.ian.weatherapp.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class LocalLists implements Serializable {
    private ArrayList<Item> list;
    public LocalLists() {
    }
    public ArrayList<Item> getList() {
        return list;
    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }

}
