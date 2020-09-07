package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sholeh.marketplacenj.respon.DataCheckout;
import com.sholeh.marketplacenj.respon.Pembeli;

import java.util.List;

public class ResDetailKeranjang {
    @SerializedName("data_keranjang")
    @Expose

    private List<DataKeranjang> dataCheckout = null;
    @SerializedName("pembeli")
    @Expose

    private Pembeli pembeli;
    @SerializedName("total")
    @Expose
    private Integer totalHarganya;

    public List<DataKeranjang> getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(List<DataKeranjang> dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Pembeli getPembeli() {
        return pembeli;
    }

    public void setPembeli(Pembeli pembeli) {
        this.pembeli = pembeli;
    }

    public Integer getTotalHarganya() {
        return totalHarganya;
    }

    public void setTotalHarganya(Integer total) {
        this.totalHarganya = total;
    }
}
