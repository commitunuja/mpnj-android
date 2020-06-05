package com.sholeh.marketplacenj.model.keranjang;

public class HeaderModel {
    String id_toko;
    String nama_toko;
//    String total;

   /* public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }*/

    public HeaderModel(String id_toko, String nama_toko) {
        this.id_toko = id_toko;
        this.nama_toko = nama_toko;
//        this.total = total;
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
}
