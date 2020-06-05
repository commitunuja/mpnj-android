package com.sholeh.marketplacenj.respon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResKeranjang {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private ValKeranjang data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ValKeranjang getData() {
        return data;
    }

    public void setData(ValKeranjang data) {
        this.data = data;
    }

}

