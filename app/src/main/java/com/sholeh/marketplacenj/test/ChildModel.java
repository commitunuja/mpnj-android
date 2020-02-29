package com.ptlmh.scannercontrol.Model;

public class ChildModel {

    String no_pelanggan;
    String tanggal;
    String kwh_tercatat;
    String status_bayar;
    String sinkron;
    String tagihan;

    public ChildModel(String no_pelanggan, String tanggal, String kwh_tercatat, String status_bayar, String sinkron, String tagihan) {
        this.no_pelanggan = no_pelanggan;
        this.tanggal = tanggal;
        this.kwh_tercatat = kwh_tercatat;
        this.status_bayar = status_bayar;
        this.sinkron = sinkron;
        this.tagihan = tagihan;
    }

    public String getTagihan() {
        return tagihan;
    }

    public void setTagihan(String tagihan) {
        this.tagihan = tagihan;
    }

    public String getSinkron() {
        return sinkron;
    }

    public void setSinkron(String sinkron) {
        this.sinkron = sinkron;
    }

    public String getNo_pelanggan() {
        return no_pelanggan;
    }

    public void setNo_pelanggan(String no_pelanggan) {
        this.no_pelanggan = no_pelanggan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKwh_tercatat() {
        return kwh_tercatat;
    }

    public void setKwh_tercatat(String kwh_tercatat) {
        this.kwh_tercatat = kwh_tercatat;
    }


    public String getStatus_bayar() {
        return status_bayar;
    }

    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }
}
