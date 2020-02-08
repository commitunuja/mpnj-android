package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResLogin {  // respon bg konsumen
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id_konsumen")
    @Expose
    private String idKonsumen;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdKonsumen() {
        return idKonsumen;
    }

    public void setIdKonsumen(String idKonsumen) {
        this.idKonsumen = idKonsumen;
    }

}
