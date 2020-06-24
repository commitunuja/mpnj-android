package com.sholeh.marketplacenj.model.pesanan;

public class PesananModel {

    String idTransaksi, namaToko, statusPembayaran, namaProduk, jumlah, harga, totalBayar, batasBayar;

    public PesananModel(String idTransaksi, String namaToko, String statusPembayaran, String namaProduk, String jumlah, String harga, String totalBayar,String batasBayar){
        this.idTransaksi = idTransaksi;
        this.namaToko = namaToko;
        this.statusPembayaran = statusPembayaran;
        this.namaProduk = namaProduk;
        this.jumlah = jumlah;
        this.harga = harga;
        this.totalBayar = totalBayar;
        this.batasBayar = batasBayar;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
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
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getBatasBayar() {
        return batasBayar;
    }

    public void setBatasBayar(String batasBayar) {
        this.batasBayar = batasBayar;
    }
}
