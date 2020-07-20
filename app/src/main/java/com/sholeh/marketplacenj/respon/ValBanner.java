package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ValBanner {
    @SerializedName("nama_banner")
    @Expose
    private String namaBanner;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("foto_banner")
    @Expose
    private String fotoBanner;
    @SerializedName("id_banner")
    @Expose
    private String idBanner;

    public String getNamaBanner() {
        return namaBanner;
    }

    public void setNamaBanner(String namaBanner) {
        this.namaBanner = namaBanner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFotoBanner() {
        return fotoBanner;
    }

    public void setFotoBanner(String fotoBanner) {
        this.fotoBanner = fotoBanner;
    }

    public String getIdBanner() {
        return idBanner;
    }

    public void setIdBanner(String idBanner) {
        this.idBanner = idBanner;
    }
}
