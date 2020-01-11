package com.sholeh.marketplacenj.api;
import com.sholeh.marketplacenj.rest.RestProduk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface API {

    @GET("produk")
    Call<RestProduk> tampilkan();


}



