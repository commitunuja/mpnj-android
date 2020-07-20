package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResCheckout {
    @SerializedName("data_keranjang")
    @Expose

    private List<DataCheckout> dataCheckout = null;
    @SerializedName("pembeli")
    @Expose

    private Pembeli pembeli;
    @SerializedName("total")
    @Expose
    private Integer totalHarganya;

    public List<DataCheckout> getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(List<DataCheckout> dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Pembeli getPembeli() {
        return pembeli;
    }

    public void setPembeli(Pembeli pembeli) {
        this.pembeli = pembeli;
    }

    public Integer getTotalHarganya() {
        return totalHarganya;
    }

    public void setTotalHarganya(Integer total) {
        this.totalHarganya = total;
    }
}
