package com.example.googlemapsuteq;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitGet {

    @GET("db")
    Call<ListModelo> getAllLocation();


}
