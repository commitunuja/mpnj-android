package com.sholeh.marketplacenj.model.api;

import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.respon.ResKeranjang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIKeranjang {
    @FormUrlEncoded
    @POST("api/keranjang")
    Call<ResKeranjang> simpanKeranjang(
            @Field("produk_id") String produkId,
            @Field("pembeli_id") String pembeliId,
            @Field("pembeli_type") String pembeliType,
            @Field("status") String status,
            @Field("jumlah") String jumlah,
            @Field("harga_jual") String hargaJual
    );

    @GET("api/produk")
    Call<Model> getProdukId(String vid_produk);


    //    @GET("/api/keranjang?role={pelapak/konsumen}&id={id ketika login}")
    @GET("api/keranjang")
    Call<JsonObject> getToko(@Query("role") String role, @Query("id") String id);
}
