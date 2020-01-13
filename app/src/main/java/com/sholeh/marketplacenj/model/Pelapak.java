
package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pelapak {

    @SerializedName("id_pelapak")
    @Expose
    private Integer idPelapak;
    @SerializedName("nama_toko")
    @Expose
    private String namaToko;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("bergabung")
    @Expose
    private String bergabung;

    public Integer getIdPelapak() {
        return idPelapak;
    }

    public void setIdPelapak(Integer idPelapak) {
        this.idPelapak = idPelapak;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getBergabung() {
        return bergabung;
    }

    public void setBergabung(String bergabung) {
        this.bergabung = bergabung;
    }

}
