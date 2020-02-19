package com.sholeh.marketplacenj.respon;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResDetailAlamat {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<ValDaftarAlamat> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ValDaftarAlamat> getData() {
        return data;
    }

    public void setData(List<ValDaftarAlamat> data) {
        this.data = data;
    }



}
