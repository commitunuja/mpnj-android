package com.sholeh.marketplacenj.respon;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResNewPassword {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id_konsumen")
        @Expose
        private String idKonsumen;
        @SerializedName("nama_lengkap")
        @Expose
        private String namaLengkap;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("remember_token")
        @Expose
        private String rememberToken;
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
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("alamat_utama")
        @Expose
        private Integer alamatUtama;

        public String getIdKonsumen() {
            return idKonsumen;
        }

        public void setIdKonsumen(String idKonsumen) {
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

        public String getRememberToken() {
            return rememberToken;
        }

        public void setRememberToken(String rememberToken) {
            this.rememberToken = rememberToken;
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

        public Integer getAlamatUtama() {
            return alamatUtama;
        }

        public void setAlamatUtama(Integer alamatUtama) {
            this.alamatUtama = alamatUtama;
        }

    }


}
