package com.sholeh.marketplacenj;
import com.sholeh.marketplacenj.model.Model;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface APIInterface {
        @GET("api/produk")
        abstract Call<List<Model>> getProduk();

    // get detail
    @Multipart
    @POST("api/produk")
    Call<Model> getProductDetials(
            @Part("id_produk") RequestBody id_produk);

    }







