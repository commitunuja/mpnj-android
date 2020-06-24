package com.sholeh.marketplacenj.model.checkout;

public class HeaderCheckout { // storebean
    String id_toko;
    String nama_toko;
    String id_kabToko;
    String kurir;
    String service;
    String ongkir;
    String etd;

    public HeaderCheckout(String id_toko, String nama_toko, String id_kabToko, String kurir, String sevice, String ongkir, String etd) {
        this.id_toko = id_toko;
        this.nama_toko = nama_toko;
        this.id_kabToko = id_kabToko;
        this.kurir = kurir;
        this.service = sevice;
        this.ongkir = ongkir;
        this.etd = etd;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
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

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getOngkir() {
        return ongkir;
    }

    public void setOngkir(String ongkir) {
        this.ongkir = ongkir;
    }
}
