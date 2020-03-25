package com.sholeh.marketplacenj.model.keranjang;

public class HeaderModel { // storebean
    String id_toko;
    String nama_toko;

    private boolean isChecked;
//    String total;

   /* public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }*/

    public HeaderModel(String id_toko, String nama_toko, boolean isChecked) {
        this.id_toko = id_toko;
        this.nama_toko = nama_toko;
        this.isChecked = isChecked;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
