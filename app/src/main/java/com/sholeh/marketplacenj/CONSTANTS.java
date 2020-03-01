package com.sholeh.marketplacenj;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CONSTANTS {

    public static final String BASE_URL="http://192.168.100/mpnj/public/"; //rubah dengan IP devicenya
  ///  public static final String BASE_URL="http://192.168.101.12/mpnj/public/"; //rubah dengan IP devicenya


//    public static final String BASE_URL = "http://192.168.43.45/mpnj/public/"; //rubah dengan IP devicenya

    public static final String QUOTE_ID = "QUOTE_ID";
    public static final String CART_ITEM_COUNT = " CART_ITEM_COUNT";

    public static final String nama_toko = "nama_toko";

    private static Retrofit retrofit;


    public static Retrofit getClient()
    {
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



