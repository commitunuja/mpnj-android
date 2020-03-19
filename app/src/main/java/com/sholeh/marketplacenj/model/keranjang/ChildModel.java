package com.sholeh.marketplacenj.model.keranjang;

public class ChildModel {


    String id_keranjang,  nama_produk, harga, jumlah, subtotal, gambar, stok;


    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public ChildModel(String id_keranjang, String nama_produk, String harga, String jumlah, String gambar, String stok) {
        this.id_keranjang = id_keranjang;
//        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.jumlah = jumlah;
        this.gambar = gambar;
        this.stok = stok;

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
