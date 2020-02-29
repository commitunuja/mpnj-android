
package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keranjang {

    @SerializedName("id_keranjang")
    @Expose
    private Integer idKeranjang;
    @SerializedName("produk_id")
    @Expose
    private Integer produkId;
    @SerializedName("pembeli_id")
    @Expose
    private Integer pembeliId;
    @SerializedName("pembeli_type")
    @Expose
    private String pembeliType;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jumlah")
    @Expose
    private Integer jumlah;
    @SerializedName("harga_jual")
    @Expose
    private Integer hargaJual;

    public Integer getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(Integer idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public Integer getProdukId() {
        return produkId;
    }

    public void setProdukId(Integer produkId) {
        this.produkId = produkId;
    }

    public Integer getPembeliId() {
        return pembeliId;
    }

    public void setPembeliId(Integer pembeliId) {
        this.pembeliId = pembeliId;
    }

    public String getPembeliType() {
        return pembeliType;
    }

    public void setPembeliType(String pembeliType) {
        this.pembeliType = pembeliType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

}
