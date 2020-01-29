package com.sholeh.marketplacenj.model.api;

import com.sholeh.marketplacenj.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIKeranjang {
    @FormUrlEncoded
    @POST("api/keranjang")
    Call<List<Model>> simpanKeranjang(
            @Field("produk_id") String produk_id,
            @Field("pembeli_id") String pembeli_id,
            @Field("pembeli_type") String pembeli_type,
            @Field("jumlah") String jumlah,
            @Field("harga_jual") String harga_jual
    );


//    @GET("/api/keranjang?role={pelapak/konsumen}&id={id ketika login}")
    @GET("api/keranjang")
     Call<List<Model>> getKeranjang();
}
