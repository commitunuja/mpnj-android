package com.sholeh.marketplacenj.model.checkout;

public class HeaderCheckout { // storebean
    String id_toko;
    String nama_toko;
    String id_kabToko;

    public HeaderCheckout(String id_toko, String nama_toko, String id_kabToko) {
        this.id_toko = id_toko;
        this.nama_toko = nama_toko;
        this.id_kabToko = id_kabToko;
    }

    public String getId_toko() {
        return id_toko;
    }

    public void setId_toko(String id_toko) {
        this.id_toko = id_toko;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getId_kabToko() {
        return id_kabToko;
    }

    public void setId_kabToko(String id_kabToko) {
        this.id_kabToko = id_kabToko;
    }
}
