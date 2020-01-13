package com.sholeh.marketplacenj.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Result
public class AllProductModel {
    @SerializedName("id_produk")
    String id_produk;
    @SerializedName("nama_produk")
    String nama_produk;
    @SerializedName("harga")
    String harga;

    public AllProductModel(){}

    public AllProductModel(String id_produk, String nama_produk, String harga) {
        this.id_produk = id_produk;
        this.nama_produk = nama_produk;
        this.harga = harga;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
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
}
/*public class AllProductModel {
    @SerializedName("example")
    @Expose
    private Example example;
    @SerializedName("kategori")
    @Expose
    private String namaProduk;
    @SerializedName("stock")
    @Expose
    private String hargaJual;

    public Example getExample(){
        return example;
    }

    public void setExample(Example example) {this.example = example; }



//    public AllProductModel() {
//    }
//
//    public AllProductModel(String id_produk, String nama_produk, String harga) {
//        this.id_produk = id_produk;
//        this.nama_produk = nama_produk;
//        this.harga = harga;
//    }

//    public String getId_produk() {
//        return id_produk;
//    }
//
//    public void setId_produk(String id_produk) {
//        this.id_produk = id_produk;
//    }
//
//    public String getNama_produk() {
//        return nama_produk;
//    }
//
//    public void setNama_produk(String nama_produk) {
//        this.nama_produk = nama_produk;
//    }
//
//    public String getHarga() {
//        return harga;
//    }
//
//    public void setHarga(String harga) {
//        this.harga = harga;
//    }
}*/
