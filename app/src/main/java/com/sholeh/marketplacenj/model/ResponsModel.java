package com.sholeh.marketplacenj.model;

import java.util.List;


public class ResponsModel {

    String kode, pesan;
    List<Model> result;

    public List<Model> getResult() {
        return result;
    }

    public void setResult(List<Model> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
