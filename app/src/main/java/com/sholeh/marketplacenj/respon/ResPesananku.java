package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResPesananku {

    @SerializedName("id_transaksi_detail")
    @Expose
    private Integer idTransaksiDetail;
    @SerializedName("nama_toko")
    @Expose
    private String namaToko;
    @SerializedName("status_pembayaran")
    @Expose
    private String statusPembayaran;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("total_bayar")
    @Expose
    private String totalBayar;
    @SerializedName("bayar_sebelum")
    @Expose
    private String bayarSebelum;

    public Integer getIdTransaksiDetail() {
        return idTransaksiDetail;
    }

    public void setIdTransaksiDetail(Integer idTransaksiDetail) {
        this.idTransaksiDetail = idTransaksiDetail;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getBayarSebelum() {
        return bayarSebelum;
    }

    public void setBayarSebelum(String bayarSebelum) {
        this.bayarSebelum = bayarSebelum;
    }

}
