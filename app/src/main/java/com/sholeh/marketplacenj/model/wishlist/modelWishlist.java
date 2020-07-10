package com.sholeh.marketplacenj.model.wishlist;

public class modelWishlist {
    private String idWishlist, idUser, idProduk, namaProduk,  kategori, fotoProduk ;
    int hargaJual, hargaDiskon;

    public modelWishlist(String idWishlist, String idUser, String idProduk, String namaProduk, int hargaJual, int hargaDiskon, String kategori, String fotoProduk) {
        this.idWishlist = idWishlist;
        this.idUser = idUser;
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.hargaJual = hargaJual;
        this.hargaDiskon = hargaDiskon;
        this.kategori = kategori;
        this.fotoProduk = fotoProduk;
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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        this.fotoProduk = fotoProduk;
    }
}
