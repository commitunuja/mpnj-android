package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResKonfirmasi {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private ValDataPembayaran data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ValDataPembayaran getData() {
        return data;
    }

    public void setData(ValDataPembayaran data) {
        this.data = data;
    }
}
