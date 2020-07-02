package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResImg {


    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<ValDataProfil> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ValDataProfil> getData() {
        return data;
    }

    public void setData(List<ValDataProfil> data) {
        this.data = data;
    }

//    @SerializedName("pesan")
//    @Expose
//    private String pesan;
//    @SerializedName("data")
//    @Expose
//    private ValImgProfil data;
//
//    public String getPesan() {
//        return pesan;
//    }
//
//    public void setPesan(String pesan) {
//        this.pesan = pesan;
//    }
//
//    public ValImgProfil getData() {
//        return data;
//    }
//
//    public void setData(ValImgProfil data) {
//        this.data = data;
//    }



}
