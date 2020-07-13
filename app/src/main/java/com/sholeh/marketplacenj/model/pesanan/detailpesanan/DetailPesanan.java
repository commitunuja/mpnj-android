package com.sholeh.marketplacenj.model.pesanan.detailpesanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailPesanan {

    @SerializedName("data")
    @Expose
    private List<ItemDetailPesanan> data = null;

    public List<ItemDetailPesanan> getData() {
        return data;
    }

    public void setData(List<ItemDetailPesanan> data) {
        this.data = data;
    }
}
