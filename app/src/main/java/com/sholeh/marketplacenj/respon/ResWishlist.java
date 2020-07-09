package com.sholeh.marketplacenj.respon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResWishlist {

    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private ValWishlist data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ValWishlist getData() {
        return data;
    }

    public void setData(ValWishlist data) {
        this.data = data;
    }
}
