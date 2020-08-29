package com.sholeh.marketplacenj.model.review;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sholeh.marketplacenj.model.Produk;

public class HeaderReview {

    @SerializedName("produk")
    @Expose
    private Produk produk;
    @SerializedName("review")
    @Expose
    private Review_ review;

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Review_ getReview() {
        return review;
    }

    public void setReview(Review_ review) {
        this.review = review;
    }

}