package com.sholeh.marketplacenj.model.pesanan.detailpesanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDetailPesanan {
    @SerializedName("id_transaksi_detail")
    @Expose
    private Integer idTransaksiDetail;
    @SerializedName("status_pembayaran")
    @Expose
    private String statusPembayaran;
    @SerializedName("total_bayar")
    @Expose
    private String totalBayar;
    @SerializedName("nama_toko")
    @Expose
    private String namaToko;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("foto_produk")
    @Expose
    private String fotoProduk;
    @SerializedName("jumlah")
    @Expose
    private String jumlah;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("kurir")
    @Expose
    private Object kurir;
    @SerializedName("ongkir")
    @Expose
    private String ongkir;
    @SerializedName("tujuan")
    @Expose
    private String tujuan;
    @SerializedName("no_pesanan")
    @Expose
    private String noPesanan;
    @SerializedName("waktu_pesan")
    @Expose
    private String waktuPesan;
    @SerializedName("bayar_sebelum")
    @Expose
    private String bayarSebelum;
    @SerializedName("status_order")
    @Expose
    private String statusOrder;

    public ItemDetailPesanan(String namaToko, String namaProduk, String jumlah, String totalBayar, String harga, String fotoProduk, String statusOrder, String bayarSebelum, Integer idTransaksiDetail, Object kurir, String ongkir, String waktuPesan) {
   this.namaToko = namaToko;
    this.namaProduk = namaProduk;
    this.jumlah = jumlah;
    this.totalBayar = totalBayar;
    this.harga = harga;
    this.fotoProduk = fotoProduk;
    this.statusOrder = statusOrder;
    this.bayarSebelum = bayarSebelum;
    this.idTransaksiDetail = idTransaksiDetail;
    this.ongkir = ongkir;
    this.kurir = kurir;
    this.waktuPesan = waktuPesan;
    }

    public Integer getIdTransaksiDetail() {
        return idTransaksiDetail;
    }

    public void setIdTransaksiDetail(Integer idTransaksiDetail) {
        this.idTransaksiDetail = idTransaksiDetail;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        this.fotoProduk = fotoProduk;
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

    public Object getKurir() {
        return kurir;
    }

    public void setKurir(Object kurir) {
        this.kurir = kurir;
    }

    public Object getOngkir() {
        return ongkir;
    }

    public void setOngkir(Object ongkir) {
        this.ongkir = ongkir;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getNoPesanan() {
        return noPesanan;
    }

    public void setNoPesanan(String noPesanan) {
        this.noPesanan = noPesanan;
    }

    public String getWaktuPesan() {
        return waktuPesan;
    }

    public void setWaktuPesan(String waktuPesan) {
        this.waktuPesan = waktuPesan;
    }

    public String getBayarSebelum() {
        return bayarSebelum;
    }

    public void setBayarSebelum(String bayarSebelum) {
        this.bayarSebelum = bayarSebelum;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

}
