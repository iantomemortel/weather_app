package com.example.ian.weatherapp.Model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private String lon = "", lat ="";
    public Coordinates() {
    }
    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }


}
