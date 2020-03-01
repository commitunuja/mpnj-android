package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResRegristasi { // respon NEW USER REGRISTRATION //
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<ValReg> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ValReg> getData() {
        return data;
    }

    public void setData(List<ValReg> data) {
        this.data = data;
    }



}


