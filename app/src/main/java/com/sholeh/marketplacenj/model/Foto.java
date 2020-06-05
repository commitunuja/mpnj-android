
package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foto {
    //respon
    @SerializedName("id_foto_poroduk")
    @Expose
    private String idFotoPoroduk;
    @SerializedName("foto_produk")
    @Expose
    private String fotoProduk;

    public Foto(String idFotoPoroduk, String fotoProduk) {
        this.idFotoPoroduk = idFotoPoroduk;
        this.fotoProduk = fotoProduk;
    }

    public String getIdFotoPoroduk() {
        return idFotoPoroduk;
    }

    public void setIdFotoPoroduk(String idFotoPoroduk) {
        this.idFotoPoroduk = idFotoPoroduk;
    }

    public String getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(String fotoProduk) {
        this.fotoProduk = fotoProduk;
    }

}
