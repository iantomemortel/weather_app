package com.example.ian.weatherapp.Model;

import java.io.Serializable;

public class Wind implements Serializable {
    private String speed = "", deg = "";

    public Wind() {
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

}
