
package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {
    @SerializedName("id_produk")
    @Expose
    private Integer idProduk;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("kategori")
    @Expose
    private Kategori kategori;
    @SerializedName("satuan")
    @Expose
    private String satuan;
    @SerializedName("berat")
    @Expose
    private Integer berat;
    @SerializedName("harga_modal")
    @Expose
    private Integer hargaModal;
    @SerializedName("harga_jual")
    @Expose
    private Integer hargaJual;
    @SerializedName("stok")
    @Expose
    private Integer stok;
    @SerializedName("diskon")
    @Expose
    private Integer diskon;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tipe_produk")
    @Expose
    private String tipeProduk;
    @SerializedName("wishlist")
    @Expose
    private Integer wishlist;
    @SerializedName("terjual")
    @Expose
    private Integer terjual;
    @SerializedName("foto")
    @Expose
    private List<Foto> foto = null;
    @SerializedName("pelapak")
    @Expose
    private Pelapak pelapak;

    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getBerat() {
        return berat;
    }

    public void setBerat(Integer berat) {
        this.berat = berat;
    }

    public Integer getHargaModal() {
        return hargaModal;
    }

    public void setHargaModal(Integer hargaModal) {
        this.hargaModal = hargaModal;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public Integer getWishlist() {
        return wishlist;
    }

    public void setWishlist(Integer wishlist) {
        this.wishlist = wishlist;
    }

    public Integer getTerjual() {
        return terjual;
    }

    public void setTerjual(Integer terjual) {
        this.terjual = terjual;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    public Pelapak getPelapak() {
        return pelapak;
    }

    public void setPelapak(Pelapak pelapak) {
        this.pelapak = pelapak;
    }

}

  /*  @SerializedName("diskon")
    @Expose
    private Integer diskon;
    @SerializedName("id_produk")
    @Expose
    private Integer idProduk;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
    @SerializedName("kategori")
    @Expose
    private Kategori kategori;
    @SerializedName("satuan")
    @Expose
    private String satuan;
    @SerializedName("berat")
    @Expose
    private Integer berat;
    @SerializedName("harga_modal")
    @Expose
    private Integer hargaModal;
    @SerializedName("harga_jual")
    @Expose
    private Integer hargaJual;
    @SerializedName("stok")
    @Expose
    private Integer stok;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tipe_produk")
    @Expose
    private String tipeProduk;
    @SerializedName("wishlist")
    @Expose
    private Integer wishlist;
    @SerializedName("terjual")
    @Expose
    private Integer terjual;
    @SerializedName("foto")
    @Expose
    private List<Foto> foto = null;
    @SerializedName("pelapak")
    @Expose
    private Pelapak pelapak;

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    public Integer getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(Integer idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Integer getBerat() {
        return berat;
    }

    public void setBerat(Integer berat) {
        this.berat = berat;
    }

    public Integer getHargaModal() {
        return hargaModal;
    }

    public void setHargaModal(Integer hargaModal) {
        this.hargaModal = hargaModal;
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public Integer getWishlist() {
        return wishlist;
    }

    public void setWishlist(Integer wishlist) {
        this.wishlist = wishlist;
    }

    public Integer getTerjual() {
        return terjual;
    }

    public void setTerjual(Integer terjual) {
        this.terjual = terjual;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    public Pelapak getPelapak() {
        return pelapak;
    }

    public void setPelapak(Pelapak pelapak) {
        this.pelapak = pelapak;
    }

}*/
