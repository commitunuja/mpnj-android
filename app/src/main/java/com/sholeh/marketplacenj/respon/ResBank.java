package com.sholeh.marketplacenj.respon;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResBank {

    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<ValBank> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ValBank> getData() {
        return data;
    }

    public void setData(List<ValBank> data) {
        this.data = data;
    }
}
