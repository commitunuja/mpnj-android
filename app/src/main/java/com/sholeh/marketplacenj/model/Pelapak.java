
package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pelapak {

    @SerializedName("id_pelapak")
    @Expose
    private Integer idPelapak;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("status_official")
    @Expose
    private String statusOfficial;
    @SerializedName("nama_toko")
    @Expose
    private String namaToko;
    @SerializedName("alamat_toko")
    @Expose
    private String alamatToko;
    @SerializedName("provinsi_id")
    @Expose
    private Integer provinsiId;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("nomor_hp")
    @Expose
    private String nomorHp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("saldo")
    @Expose
    private Integer saldo;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getIdPelapak() {
        return idPelapak;
    }

    public void setIdPelapak(Integer idPelapak) {
        this.idPelapak = idPelapak;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusOfficial() {
        return statusOfficial;
    }

    public void setStatusOfficial(String statusOfficial) {
        this.statusOfficial = statusOfficial;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
    }

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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

}
