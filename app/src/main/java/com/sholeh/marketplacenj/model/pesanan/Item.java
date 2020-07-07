package com.sholeh.marketplacenj.model.pesanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("harga_jual")
    @Expose
    private String hargaJual;
    @SerializedName("status_order")
    @Expose
    private String statusOrder;

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

}