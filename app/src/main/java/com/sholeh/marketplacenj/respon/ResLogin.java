package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class
ResLogin {  // respon bg konsumen

    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("nomor_hp")
    @Expose
    private String nomorHp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("id_user")
    @Expose
    private Integer idKonsumen;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getIdKonsumen() {
        return idKonsumen;
    }

    public void setIdKonsumen(Integer idKonsumen) {
        this.idKonsumen = idKonsumen;
    }

}
