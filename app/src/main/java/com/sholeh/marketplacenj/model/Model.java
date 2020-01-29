
package com.sholeh.marketplacenj.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("id_produk")
    @Expose
    private Integer idProduk;
    @SerializedName("nama_produk")
    @Expose
    private String namaProduk;
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
    @SerializedName("diskon")
    @Expose
    private Integer diskon;
    @SerializedName("stok")
    @Expose
    private Integer stok;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("foto")
    @Expose
    private Object foto;
    @SerializedName("tipe_produk")
    @Expose
    private String tipeProduk;
    @SerializedName("pelapak_id")
    @Expose
    private Integer pelapakId;
    @SerializedName("wishlist")
    @Expose
    private Integer wishlist;
    @SerializedName("terjual")
    @Expose
    private Integer terjual;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("kategori_produk_id")
    @Expose
    private Integer kategoriProdukId;
    @SerializedName("foto_produk")
    @Expose
    private List<FotoProduk> fotoProduk = null;
    @SerializedName("kategori")
    @Expose
    private Kategori kategori;
    @SerializedName("pelapak")
    @Expose
    private Pelapak pelapak;
    @SerializedName("keranjang")
    @Expose
    private List<Keranjang> keranjang = new ArrayList<>();

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

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
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

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public void setTipeProduk(String tipeProduk) {
        this.tipeProduk = tipeProduk;
    }

    public Integer getPelapakId() {
        return pelapakId;
    }

    public void setPelapakId(Integer pelapakId) {
        this.pelapakId = pelapakId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getKategoriProdukId() {
        return kategoriProdukId;
    }

    public void setKategoriProdukId(Integer kategoriProdukId) {
        this.kategoriProdukId = kategoriProdukId;
    }

    public List<FotoProduk> getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(List<FotoProduk> fotoProduk) {
        this.fotoProduk = fotoProduk;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Pelapak getPelapak() {
        return pelapak;
    }

    public void setPelapak(Pelapak pelapak) {
        this.pelapak = pelapak;
    }

    public List<Keranjang> getKeranjang() {
        return keranjang;
    }

    public void setKeranjang(List<Keranjang> keranjang) {
        this.keranjang = keranjang;
    }

}
