package com.example.myapplication.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiService {

    @GET("users/{id}")
    Call<UserDto> getUserById(@Path("id") Long id);
}
