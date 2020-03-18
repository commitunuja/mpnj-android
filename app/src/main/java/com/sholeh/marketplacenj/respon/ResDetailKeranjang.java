package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResDetailKeranjang {
    @SerializedName("data_keranjang")
    @Expose

    private List<DataKeranjang> dataKeranjang = null;
    @SerializedName("pembeli")
    @Expose

    private Pembeli pembeli;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<DataKeranjang> getDataKeranjang() {
        return dataKeranjang;
    }

    public void setDataKeranjang(List<DataKeranjang> dataKeranjang) {
        this.dataKeranjang = dataKeranjang;
    }

    public Pembeli getPembeli() {
        return pembeli;
    }

    public void setPembeli(Pembeli pembeli) {
        this.pembeli = pembeli;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
