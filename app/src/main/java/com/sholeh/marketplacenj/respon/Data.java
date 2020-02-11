package com.sholeh.marketplacenj.respon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {


    @SerializedName("id_konsumen")
    @Expose
    private String idKonsumen;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("alamat_utama")
    @Expose
    private AlamatUtama alamatUtama;
    @SerializedName("nomer")
    @Expose
    private String nomer;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("alamat_lain")
    @Expose
    private AlamatLain alamatLain;

    public String getIdKonsumen() {
        return idKonsumen;
    }

    public void setIdKonsumen(String idKonsumen) {
        this.idKonsumen = idKonsumen;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public AlamatUtama getAlamatUtama() {
        return alamatUtama;
    }

    public void setAlamatUtama(AlamatUtama alamatUtama) {
        this.alamatUtama = alamatUtama;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AlamatLain getAlamatLain() {
        return alamatLain;
    }

    public void setAlamatLain(AlamatLain alamatLain) {
        this.alamatLain = alamatLain;
    }


}
