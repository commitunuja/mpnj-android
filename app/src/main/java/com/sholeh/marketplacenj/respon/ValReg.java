package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValReg {
        @SerializedName("nama_lengkap")
        @Expose
        private String namaLengkap;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("provinsi_id")
        @Expose
        private String provinsiId;
        @SerializedName("city_id")
        @Expose
        private String cityId;
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
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id_konsumen")
        @Expose
        private Integer idKonsumen;

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

        public String getProvinsiId() {
            return provinsiId;
        }

        public void setProvinsiId(String provinsiId) {
            this.provinsiId = provinsiId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
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

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getIdKonsumen() {
            return idKonsumen;
        }

        public void setIdKonsumen(Integer idKonsumen) {
            this.idKonsumen = idKonsumen;
        }

}
