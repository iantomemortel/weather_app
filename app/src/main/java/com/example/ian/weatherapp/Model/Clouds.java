package com.example.ian.weatherapp.Model;

import java.io.Serializable;

public class Clouds implements Serializable {
    private String all = "";

    public Clouds() {
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

}
