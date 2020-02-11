package com.sholeh.marketplacenj.respon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlamatLain {
    @SerializedName("id_alamat")
    @Expose
    private Integer idAlamat;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("provinsi")
    @Expose
    private String provinsi;
    @SerializedName("kode_pos")
    @Expose
    private String kodePos;

    public Integer getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(Integer idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }


}
