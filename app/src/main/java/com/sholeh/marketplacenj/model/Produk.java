package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Produk {

    @SerializedName("produk_id")
    @Expose
    private Integer produkId;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("pelapak")
    @Expose
    private String pelapak;

    public Integer getProdukId() {
        return produkId;
    }

    public void setProdukId(Integer produkId) {
        this.produkId = produkId;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getPelapak() {
        return pelapak;
    }

    public void setPelapak(String pelapak) {
        this.pelapak = pelapak;
    }

}