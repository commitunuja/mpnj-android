package com.sholeh.marketplacenj.model.pesanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataPesanan {

    @SerializedName("kode_invoice")
    @Expose
    private String kodeInvoice;
    @SerializedName("nama_toko")
    @Expose
    private String namaToko;
    @SerializedName("jumlah_pesanan")
    @Expose
    private Integer jumlahPesanan;
    @SerializedName("waktu_transaksi")
    @Expose
    private String waktuTransaksi;
    @SerializedName("total_pembayaran")
    @Expose
    private Integer totalPembayaran;
    @SerializedName("item")
    @Expose
    private List<Item> item = null;

    public String getKodeInvoice() {
        return kodeInvoice;
    }

    public void setKodeInvoice(String kodeInvoice) {
        this.kodeInvoice = kodeInvoice;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public Integer getJumlahPesanan() {
        return jumlahPesanan;
    }

    public void setJumlahPesanan(Integer jumlahPesanan) {
        this.jumlahPesanan = jumlahPesanan;
    }

    public String getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(String waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }

    public Integer getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(Integer totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
