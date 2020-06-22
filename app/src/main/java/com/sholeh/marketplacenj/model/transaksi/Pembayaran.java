package com.sholeh.marketplacenj.model.transaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pembayaran {

    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose

    private List<MetodePembayaran> data = null;

    public Pembayaran(List<MetodePembayaran> data) {
        this.data = data;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<MetodePembayaran> getData() {
        return data;
    }

    public void setData(List<MetodePembayaran> data) {
        this.data = data;
    }

}