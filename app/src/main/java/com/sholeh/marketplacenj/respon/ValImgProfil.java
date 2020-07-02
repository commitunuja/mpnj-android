package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValImgProfil {

    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("nomor_hp")
    @Expose
    private String nomorHp;
    @SerializedName("foto_profil")
    @Expose
    private String fotoProfil;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified_at")
    @Expose
    private String emailVerifiedAt;
    @SerializedName("status_official")
    @Expose
    private String statusOfficial;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("nama_toko")
    @Expose
    private String namaToko;
    @SerializedName("foto_toko")
    @Expose
    private Object fotoToko;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("saldo")
    @Expose
    private String saldo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("alamat_utama")
    @Expose
    private String alamatUtama;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("alamat_toko")
    @Expose
    private String alamatToko;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getStatusOfficial() {
        return statusOfficial;
    }

    public void setStatusOfficial(String statusOfficial) {
        this.statusOfficial = statusOfficial;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public Object getFotoToko() {
        return fotoToko;
    }

    public void setFotoToko(Object fotoToko) {
        this.fotoToko = fotoToko;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamatUtama() {
        return alamatUtama;
    }

    public void setAlamatUtama(String alamatUtama) {
        this.alamatUtama = alamatUtama;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
    }

}
