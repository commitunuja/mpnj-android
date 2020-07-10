package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValReg {
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
    private Object fotoProfil;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("status_official")
    @Expose
    private String statusOfficial;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("nama_toko")
    @Expose
    private Object namaToko;
    @SerializedName("foto_toko")
    @Expose
    private Object fotoToko;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("saldo")
    @Expose
    private Integer saldo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("alamat_utama")
    @Expose
    private Object alamatUtama;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("alamat_toko")
    @Expose
    private Object alamatToko;

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

    public Object getFotoProfil() {
        return fotoProfil;
    }

    public void setFotoProfil(Object fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
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

    public Object getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(Object namaToko) {
        this.namaToko = namaToko;
    }

    public Object getFotoToko() {
        return fotoToko;
    }

    public void setFotoToko(Object fotoToko) {
        this.fotoToko = fotoToko;
    }

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAlamatUtama() {
        return alamatUtama;
    }

    public void setAlamatUtama(Object alamatUtama) {
        this.alamatUtama = alamatUtama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(Object alamatToko) {
        this.alamatToko = alamatToko;
    }

}
