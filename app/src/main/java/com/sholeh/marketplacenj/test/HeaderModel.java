package com.sholeh.marketplacenj.test;

public class HeaderModel {
    String no_pelanggan;
    String nama_pelanggan;

    public HeaderModel(String no_pelanggan, String nama_pelanggan) {
        this.no_pelanggan = no_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }
}
