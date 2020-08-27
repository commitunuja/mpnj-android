package com.sholeh.marketplacenj.model.review;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review_ {

    @SerializedName("id_review")
    @Expose
    private Integer idReview;
    @SerializedName("id_produk")
    @Expose
    private Integer idProduk;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("bintang")
    @Expose
    private String bintang;
    @SerializedName("foto_review")
    @Expose
    private String fotoReview;

    public Review_(String review) {
        this.review =review;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getBintang() {
        return bintang;
    }

    public void setBintang(String bintang) {
        this.bintang = bintang;
    }

    public String getFotoReview() {
        return fotoReview;
    }

    public void setFotoReview(String fotoReview) {
        this.fotoReview = fotoReview;
    }

}