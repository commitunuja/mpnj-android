package com.sholeh.marketplacenj.model.pesanan;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pembayaran {

    @SerializedName("data_pesanan")
    @Expose
    private List<DataPesanan> dataPesanan = null;

    public List<DataPesanan> getDataPesanan() {
        return dataPesanan;
    }

    public void setDataPesanan(List<DataPesanan> dataPesanan) {
        this.dataPesanan = dataPesanan;
    }

}