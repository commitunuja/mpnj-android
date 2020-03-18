package com.sholeh.marketplacenj.respon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemKeranjang {

    @SerializedName("id_keranjang")
    @Expose
    private String idKeranjang;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("harga_jual")
    @Expose
    private String hargaJual;
    @SerializedName("diskon")
    @Expose
    private String diskon;
    @SerializedName("id_produk")
    @Expose
    private Integer idProduk;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("foto")
    @Expose
    private String foto;


    public ItemKeranjang(String idKeranjang, String jumlah, String hargaJual, String namaProduk, String foto) {
        this.idKeranjang = idKeranjang;
        this.jumlah = jumlah;
        this.hargaJual = hargaJual;
        this.namaProduk = namaProduk;
        this.foto= foto;
    }

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

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
}
