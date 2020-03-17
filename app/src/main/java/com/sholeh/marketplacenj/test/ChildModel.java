package com.sholeh.marketplacenj.test;

public class ChildModel {


    String id_toko,  nama_produk, harga, jumlah, subtotal, gambar;


    public ChildModel(String id_toko, String nama_produk, String harga, String jumlah, String gambar) {
        this.id_toko = id_toko;
//        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.jumlah = jumlah;
        this.gambar = gambar;

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

    public String getId_toko() {
        return id_toko;
    }

    public void setId_toko(String id_toko) {
        this.id_toko = id_toko;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }


}
