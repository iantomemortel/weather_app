package com.example.ian.weatherapp.data;

import android.arch.persistence.room.TypeConverter;

import com.example.ian.weatherapp.entity.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class WeatherTypeConverters {

    @TypeConverter
    public static List<Weather> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Weather>>() {}.getType();

        return new Gson().fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Weather> someObjects) {
        return new Gson().toJson(someObjects);
    }
}
