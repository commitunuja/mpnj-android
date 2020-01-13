package com.sholeh.marketplacenj;
import com.sholeh.marketplacenj.model.AllProductModel;
import com.sholeh.marketplacenj.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {
        @GET("api/produk")
        abstract Call<List<Model>> getProduk();
    }



