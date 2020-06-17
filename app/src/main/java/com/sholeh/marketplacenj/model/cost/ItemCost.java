package com.sholeh.marketplacenj.model.cost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCost {

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
