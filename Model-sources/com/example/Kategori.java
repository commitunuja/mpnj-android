
package com.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategori {

    @SerializedName("id_kategori_produk")
    @Expose
    private Integer idKategoriProduk;
    @SerializedName("nama_kategori")
    @Expose
    private String namaKategori;

    public Integer getIdKategoriProduk() {
        return idKategoriProduk;
    }

    public void setIdKategoriProduk(Integer idKategoriProduk) {
        this.idKategoriProduk = idKategoriProduk;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

}
