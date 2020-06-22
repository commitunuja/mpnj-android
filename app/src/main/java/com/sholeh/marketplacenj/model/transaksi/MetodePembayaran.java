package com.sholeh.marketplacenj.model.transaksi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetodePembayaran {
    @SerializedName("kode_transaksi")
    @Expose
    private String kodeTransaksi;
    @SerializedName("pembeli")
    @Expose
    private Pembeli pembeli;
    @SerializedName("total_bayar")
    @Expose
    private String totalBayar;
    @SerializedName("id_rekening")
    @Expose
    private Object idRekening;

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public Pembeli getPembeli() {
        return pembeli;
    }

    public void setPembeli(Pembeli pembeli) {
        this.pembeli = pembeli;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public Object getIdRekening() {
        return idRekening;
    }

    public void setIdRekening(Object idRekening) {
        this.idRekening = idRekening;
    }

}
