package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValDaftarAlamat {
    @SerializedName("id_alamat")
    @Expose
    private String idAlamat;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomor_telepon")
    @Expose
    private String nomorTelepon;
    @SerializedName("provinsi_id")
    @Expose
    private Integer provinsiId;
    @SerializedName("nama_provinsi")
    @Expose
    private String namaProvinsi;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("nama_kota")
    @Expose
    private String namaKota;
    @SerializedName("kecamatan_id")
    @Expose
    private Integer kecamatanId;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("alamat_lengkap")
    @Expose
    private String alamatLengkap;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("status")
    @Expose
    private String status;

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public Integer getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(Integer provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public void setNamaProvinsi(String namaProvinsi) {
        this.namaProvinsi = namaProvinsi;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public Integer getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(Integer kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
