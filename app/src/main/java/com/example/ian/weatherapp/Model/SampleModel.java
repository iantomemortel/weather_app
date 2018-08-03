package com.example.ian.weatherapp.Model;

import java.io.Serializable;

public class SampleModel implements Serializable {
    public String location, temp, weather;

    public SampleModel() {
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

}
