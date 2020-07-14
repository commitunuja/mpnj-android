package com.sholeh.marketplacenj.model.wishlist;

import com.sholeh.marketplacenj.model.Kategori;

public class modelWishlist {
    private String idWishlist, idUser, idProduk, namaProduk, keterangan, fotoProduk, kabPenjual ;
    int hargaJual, hargaDiskon, stok, terjual;
    Kategori kategori;

    public modelWishlist(String idWishlist, String idUser, String idProduk, String namaProduk, int hargaJual, int hargaDiskon, int stok, Kategori kategori, String deskripsi, int terjual, String kabPenjual, String fotoProduk) {
        this.idWishlist = idWishlist;
        this.idUser = idUser;
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.hargaJual = hargaJual;
        this.hargaDiskon = hargaDiskon;
        this.stok = stok;
        this.kategori = kategori;
        this.keterangan = deskripsi;
        this.terjual = terjual;
        this.kabPenjual = kabPenjual;
        this.fotoProduk = fotoProduk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
    }

    public void setHargaDiskon(int hargaDiskon) {
        this.hargaDiskon = hargaDiskon;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(String idWishlist) {
        this.idWishlist = idWishlist;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Integer getHargaDiskon() {
        return hargaDiskon;
    }

    public void setHargaDiskon(Integer hargaDiskon) {
        this.hargaDiskon = hargaDiskon;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        this.fotoProduk = fotoProduk;
    }

    public int getTerjual() {
        return terjual;
    }

    public void setTerjual(int terjual) {
        this.terjual = terjual;
    }


    public String getKabPenjual() {
        return kabPenjual;
    }

    public void setKabPenjual(String kabPenjual) {
        this.kabPenjual = kabPenjual;
    }

}
