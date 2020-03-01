package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValDataProfil {

    @SerializedName("id_konsumen")
    @Expose
    private Integer idKonsumen;
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
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("alamat_utama")
    @Expose
    private Integer alamatUtama;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("daftar_alamat")
    @Expose
    private List<ValDaftarAlamat> daftarAlamat = null;

    public Integer getIdKonsumen() {
        return idKonsumen;
    }

    public void setIdKonsumen(Integer idKonsumen) {
        this.idKonsumen = idKonsumen;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAlamatUtama() {
        return alamatUtama;
    }

    public void setAlamatUtama(Integer alamatUtama) {
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

    public List<ValDaftarAlamat> getDaftarAlamat() {
        return daftarAlamat;
    }

    public void setDaftarAlamat(List<ValDaftarAlamat> daftarAlamat) {
        this.daftarAlamat = daftarAlamat;
    }
}
