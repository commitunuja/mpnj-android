package com.sholeh.marketplacenj.model.wishlist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValResWishllist {
    @SerializedName("id_wishlist")
    @Expose
    private String idWishlist;

    @SerializedName("id_user")
    @Expose
    private String idUser;

    @SerializedName("id_produk")
    @Expose
    private String idProduk;

    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;

    @SerializedName("harga_jual")
    @Expose
    private Integer hargaJual;

    @SerializedName("diskon")
    @Expose
    private Integer diskon;

    @SerializedName("kategori")
    @Expose
    private String kategori;

    @SerializedName("foto_produk")
    @Expose
    private String fotoProduk;

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

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
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
