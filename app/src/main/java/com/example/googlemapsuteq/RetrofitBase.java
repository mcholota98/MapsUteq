package com.example.googlemapsuteq;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {

    public static final String Base_url = "https://my-json-server.typicode.com/mcholota98/jsonMapsUteq/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
