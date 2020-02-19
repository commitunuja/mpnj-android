package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResAlamat {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<ValAddress> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ValAddress> getData() {
        return data;
    }

    public void setData(List<ValAddress> data) {
        this.data = data;
    }



}
