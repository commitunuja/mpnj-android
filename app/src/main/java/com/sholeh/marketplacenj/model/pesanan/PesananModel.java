package com.sholeh.marketplacenj.model.pesanan;

public class PesananModel {

    String idTransaksi, nama_toko, status_pembayaran, nama_produk, jumlah, harga, total_bayar, bayar_sebelum, foto_produk;



    public PesananModel(String idTransaksi, String nama_toko, String status_pembayaran, String nama_produk, String jumlah, String harga, String total_bayar, String bayar_sebelum, String foto_produk) {
        this.idTransaksi = idTransaksi;
        this.nama_toko = nama_toko;
        this.status_pembayaran = status_pembayaran;
        this.nama_produk = nama_produk;
        this.jumlah = jumlah;
        this.harga = harga;
        this.total_bayar = total_bayar;
        this.bayar_sebelum = bayar_sebelum;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNamaToko() {
        return nama_toko;
    }

    public void setNamaToko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getStatusPembayaran() {
        return status_pembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.status_pembayaran = statusPembayaran;
    }

    public String getNamaProduk() {
        return nama_produk;
    }

    public void setNamaProduk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotalBayar() {
        return total_bayar;
    }

    public void setTotalBayar(String total_bayar) {
        this.total_bayar = total_bayar;
    }

    public String getBatasBayar() {
        return bayar_sebelum;
    }

    public void setBatasBayar(String batasBayar) {
        this.bayar_sebelum = batasBayar;
    }
}
