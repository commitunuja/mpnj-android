package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ValDataPembayaran {
    @SerializedName("kode_transaksi")
    @Expose
    private String kodeTransaksi;
    @SerializedName("total_transfer")
    @Expose
    private String totalTransfer;
    @SerializedName("rekening_admin_id")
    @Expose
    private String rekeningAdminId;
    @SerializedName("nama_pengirim")
    @Expose
    private String namaPengirim;
    @SerializedName("tanggal_transfer")
    @Expose
    private String tanggalTransfer;
    @SerializedName("bukti_transfer")
    @Expose
    private String buktiTransfer;

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public String getTotalTransfer() {
        return totalTransfer;
    }

    public void setTotalTransfer(String totalTransfer) {
        this.totalTransfer = totalTransfer;
    }

    public String getRekeningAdminId() {
        return rekeningAdminId;
    }

    public void setRekeningAdminId(String rekeningAdminId) {
        this.rekeningAdminId = rekeningAdminId;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getTanggalTransfer() {
        return tanggalTransfer;
    }

    public void setTanggalTransfer(String tanggalTransfer) {
        this.tanggalTransfer = tanggalTransfer;
    }

    public String getBuktiTransfer() {
        return buktiTransfer;
    }

    public void setBuktiTransfer(String buktiTransfer) {
        this.buktiTransfer = buktiTransfer;
    }
}
