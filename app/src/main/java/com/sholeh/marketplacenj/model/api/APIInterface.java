package com.sholeh.marketplacenj.model.api;

import com.sholeh.marketplacenj.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {
    @GET("api/produk")
        abstract Call<List<Model>> getProduk();


    // get detail
    /*@Multipart
    @GET("api/produk/{id_produk}")
    Call<Model> getIdProdukDetail(
            @Part("id_produk") int getIdproduk);*/

  /*  @FormUrlEncoded
    @POST("api/keranjang")
    Call<List<Model>> sendData(
            @Field("produk_id") String produk_id,
            @Field("konsumen_id") String konsumen_id,
            @Field("jumlah") String jumlah,
            @Field("harga_jual") String harga_jual,
            @Field("status") String status
    );*/

    @GET("api/keranjang")
    Call<List<Model>> getData();

}







