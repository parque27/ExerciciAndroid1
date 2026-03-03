package com.example.myapplication;
import android.app.Application;
import com.example.myapplication.api.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class App extends Application {
    ApiService appService;
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        appService = retrofit.create(ApiService.class);
    }
    public ApiService getAPI() {
        return appService;
    }
}