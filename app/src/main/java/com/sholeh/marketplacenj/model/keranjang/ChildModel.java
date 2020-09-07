package com.sholeh.marketplacenj.model.keranjang;

public class ChildModel {
    String id_keranjang,  id_produk, nama_produk,  gambar, keterangan, kategori, terjual;
    int harga, diskon , stok, jumlah;

    private boolean isChecked;

    public ChildModel(String id_keranjang, String id_produk, String nama_produk, String kategori, String keterangan , int harga, int diskon , int jumlah, String gambar, int stok, String terjual,  boolean isChecked) {
        this.id_keranjang = id_keranjang;
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.kategori = kategori;
        this.keterangan = keterangan;
        this.harga = harga;
        this.diskon = diskon;
        this.jumlah = jumlah;
        this.gambar = gambar;
        this.stok = stok;
        this.terjual = terjual;
        this.isChecked = isChecked;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTerjual() {
        return terjual;
    }

    public void setTerjual(String terjual) {
        this.terjual = terjual;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

//    public String getId_produk() {
//        return id_produk;
//    }
//
//    public void setId_produk(String id_produk) {
//        this.id_produk = id_produk;
//    }

    public String getId_keranjang() {
        return id_keranjang;
    }

    public void setId_keranjang(String id_keranjang) {
        this.id_keranjang = id_keranjang;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
