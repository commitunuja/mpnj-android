package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValAddress {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nomor_telepon")
    @Expose
    private String nomorTelepon;
    @SerializedName("provinsi_id")
    @Expose
    private String provinsiId;
    @SerializedName("nama_provinsi")
    @Expose
    private String namaProvinsi;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("nama_kota")
    @Expose
    private String namaKota;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;
    @SerializedName("kecamatan_id")
    @Expose
    private Integer kecamatanId;
    @SerializedName("alamat_lengkap")
    @Expose
    private String alamatLengkap;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("id_alamat")
    @Expose
    private Integer idAlamat;

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

    public String getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(String provinsiId) {
        this.provinsiId = provinsiId;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public void setNamaProvinsi(String namaProvinsi) {
        this.namaProvinsi = namaProvinsi;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public Integer getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(Integer kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(Integer idAlamat) {
        this.idAlamat = idAlamat;
    }
}
