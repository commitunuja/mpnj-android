package com.sholeh.marketplacenj.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemKeranjang {

    @SerializedName("id_keranjang")
    @Expose
    private String idKeranjang;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("id_produk")
    @Expose
    private String idProduk;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("kategori")
    @Expose
    private Kategori kategori;
    @SerializedName("satuan")
    @Expose
    private String satuan;
    @SerializedName("berat")
    @Expose
    private String berat;
    @SerializedName("harga_modal")
    @Expose
    private String hargaModal;
    @SerializedName("harga_jual")
    @Expose
    private String hargaJual;
    @SerializedName("stok")
    @Expose
    private String stok;
    @SerializedName("diskon")
    @Expose
    private String diskon;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tipe_produk")
    @Expose
    private String tipeProduk;
    @SerializedName("wishlist")
    @Expose
    private String wishlist;
    @SerializedName("terjual")
    @Expose
    private String terjual;
    @SerializedName("foto")
    @Expose
    private List<Foto> foto = null;
    @SerializedName("pelapak")
    @Expose
    private Pelapak pelapak;

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getHargaModal() {
        return hargaModal;
    }

    public void setHargaModal(String hargaModal) {
        this.hargaModal = hargaModal;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

    public String getTerjual() {
        return terjual;
    }

    public void setTerjual(String terjual) {
        this.terjual = terjual;
    }


    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }


    public Pelapak getPelapak() {
        return pelapak;
    }

    public void setPelapak(Pelapak pelapak) {
        this.pelapak = pelapak;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getIdKeranjang() {
        return idKeranjang;
    }

    public void setIdKeranjang(String idKeranjang) {
        this.idKeranjang = idKeranjang;
    }

}