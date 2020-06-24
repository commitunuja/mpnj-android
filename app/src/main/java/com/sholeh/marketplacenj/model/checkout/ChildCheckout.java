package com.sholeh.marketplacenj.model.checkout;

public class ChildCheckout {
    String id_keranjang,  nama_produk, subtotal, gambar;
    int harga, diskon , stok, jumlah;
    public static final  int STATUS_INVALID=0;
    public static final  int STATUS_VALID=1;


    public ChildCheckout(String id_keranjang, String nama_produk, int harga, int diskon , int jumlah, String gambar) {
        this.id_keranjang = id_keranjang;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.diskon = diskon;
        this.jumlah = jumlah;
        this.gambar = gambar;


    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

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

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }


}
