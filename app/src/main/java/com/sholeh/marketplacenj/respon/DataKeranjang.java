package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataKeranjang {

    @SerializedName("id_toko")
    @Expose
    private String idToko;

    @SerializedName("id_kabupaten")
    @Expose
    private String idKabupaten;

    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    @SerializedName("total_berat")
    @Expose
    private String totalBerat;

    @SerializedName("kurir")
    @Expose
    private String kurir;

    @SerializedName("service")
    @Expose
    private String service;

    @SerializedName("ongkir")
    @Expose
    private String ongkir;

    @SerializedName("etd")
    @Expose
    private String etd;

    @SerializedName("nama_toko")
    @Expose
    private String namaToko;

    @SerializedName("item")
    @Expose
    private List<ItemKeranjang> item = null;

    public String getIdToko() {
        return idToko;
    }

    public void setIdToko(String idToko) {
        this.idToko = idToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public List<ItemKeranjang> getItem() {
        return item;
    }

    public void setItem(List<ItemKeranjang> item) {
        this.item = item;
    }

    public String getIdKabupaten() {
        return idKabupaten;
    }

    public void setIdKabupaten(String idKabupaten) {
        this.idKabupaten = idKabupaten;
    }

    public String getTotalBerat() {
        return totalBerat;
    }

    public void setTotalBerat(String totalBerat) {
        this.totalBerat = totalBerat;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getOngkir() {
        return ongkir;
    }

    public void setOngkir(String ongkir) {
        this.ongkir = ongkir;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }
}
