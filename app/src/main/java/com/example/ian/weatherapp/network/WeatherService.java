package com.example.ian.weatherapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherService {
    @GET
    Call<ResponseBody> downloadFile(@Url String fileURL);
}
