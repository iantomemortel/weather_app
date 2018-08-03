package com.example.ian.weatherapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lists implements Serializable {
    private ArrayList<Location> list;
    public Lists() {
    }
    public ArrayList<Location> getList() {
        return list;
    }

    public void setList(ArrayList<Location> list) {
        this.list = list;
    }

}
