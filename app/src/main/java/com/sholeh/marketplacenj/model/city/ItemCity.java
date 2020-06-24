package com.sholeh.marketplacenj.model.city;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCity {

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
