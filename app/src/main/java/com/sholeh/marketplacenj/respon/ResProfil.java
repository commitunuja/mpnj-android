package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResProfil {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

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
        private List<DaftarAlamat> daftarAlamat = null;

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

        public List<DaftarAlamat> getDaftarAlamat() {
            return daftarAlamat;
        }

        public void setDaftarAlamat(List<DaftarAlamat> daftarAlamat) {
            this.daftarAlamat = daftarAlamat;
        }

    }

    public class DaftarAlamat {

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
}


