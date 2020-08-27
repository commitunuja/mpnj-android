package com.sholeh.marketplacenj.model.review;

public class ReviewModel {
    private String id_review, id_produk, id_user, review, bintang, foto_review, reviewer;

    public String getId_review() {
        return id_review;
    }

    public void setId_review(String id_review) {
        this.id_review = id_review;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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

    public String getFoto_review() {
        return foto_review;
    }

    public void setFoto_review(String foto_review) {
        this.foto_review = foto_review;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public ReviewModel (String id_review, String id_produk, String id_user, String bintang, String foto_review, String reviewer){
        this.id_review = id_review;
        this.id_produk = id_produk;
        this.id_user = id_user;
        this.bintang = bintang;
        this.foto_review = foto_review;
        this.reviewer = reviewer;
    }
}
