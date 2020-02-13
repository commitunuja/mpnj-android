package com.sholeh.marketplacenj.model;

public class AlamatModel {
    private String idAlamat,  namaLengkap, nomorHP, alamatLengkap,  namaKota, namaProvinsi, KodePos;

    public AlamatModel(String idAlamat, String namaLengkap, String nomorHP, String alamatLengkap, String namaKota, String namaProvinsi, String kodePos) {
        this.idAlamat = idAlamat;
        this.namaLengkap = namaLengkap;
        this.nomorHP = nomorHP;
        this.alamatLengkap = alamatLengkap;
        this.namaKota = namaKota;
        this.namaProvinsi = namaProvinsi;
        KodePos = kodePos;
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
        return KodePos;
    }

    public void setKodePos(String kodePos) {
        KodePos = kodePos;
    }
}
