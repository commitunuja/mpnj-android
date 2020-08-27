package com.sholeh.marketplacenj.model.review;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<HeaderReview> data = null;

    public Review(String review) {
        this.pesan = review;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<HeaderReview> getData() {
        return data;
    }

    public void setData(List<HeaderReview> data) {
        this.data = data;
    }

}