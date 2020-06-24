package com.sholeh.marketplacenj.model;

public class AlamatModel {
    private String idAlamat,  namaLengkap, nomorHP, alamatLengkap,  namaKec, namaKota, namaProvinsi, kodePos, status;

    public AlamatModel(String idAlamat, String namaLengkap, String nomorHP, String alamatLengkap, String namaKec, String namaKota, String namaProvinsi, String kodePos, String status) {
        this.idAlamat = idAlamat;
        this.namaLengkap = namaLengkap;
        this.nomorHP = nomorHP;
        this.alamatLengkap = alamatLengkap;
        this.namaKec = namaKec;
        this.namaKota = namaKota;
        this.namaProvinsi = namaProvinsi;
        this.kodePos = kodePos;
        this.status = status;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public void setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
    }

    public String getAlamatLengkap() {
        return alamatLengkap;
    }

    public void setAlamatLengkap(String alamatLengkap) {
        this.alamatLengkap = alamatLengkap;
    }

    public String getNamaKec() {
        return namaKec;
    }

    public void setNamakec(String namaKec) {
        this.namaKec = namaKec;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getNamaProvinsi() {
        return namaProvinsi;
    }

    public void setNamaProvinsi(String namaProvinsi) {
        this.namaProvinsi = namaProvinsi;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
