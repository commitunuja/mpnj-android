package com.sholeh.marketplacenj.model.subdistrict;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ItemKec {
    @SerializedName("rajaongkir")
    @Expose
    private Rajaongkir rajaongkir;

    public Rajaongkir getRajaongkir() {
        return rajaongkir;
    }

    public void setRajaongkir(Rajaongkir rajaongkir) {
        this.rajaongkir = rajaongkir;
    }


}
