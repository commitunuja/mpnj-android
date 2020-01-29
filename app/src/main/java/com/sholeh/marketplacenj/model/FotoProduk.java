
package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FotoProduk {

    @SerializedName("id_foto_produk")
    @Expose
    private Integer idFotoProduk;
    @SerializedName("foto_produk")
    @Expose
    private String fotoProduk;
    @SerializedName("produk_id")
    @Expose
    private Integer produkId;

    public Integer getIdFotoProduk() {
        return idFotoProduk;
    }

    public void setIdFotoProduk(Integer idFotoProduk) {
        this.idFotoProduk = idFotoProduk;
    }

    public String getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        this.fotoProduk = fotoProduk;
    }

    public Integer getProdukId() {
        return produkId;
    }

    public void setProdukId(Integer produkId) {
        this.produkId = produkId;
    }

}
