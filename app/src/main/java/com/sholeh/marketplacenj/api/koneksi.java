package com.sholeh.marketplacenj.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class koneksi {
    public static final String BASE_URL = "http://localhost:8000/api/";
    private static Retrofit retrofit;


    public static Retrofit getClient() {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }

}
