package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResRekAdmin {
    @SerializedName("data")
    @Expose
    private List<ValRekAdmin> data = null;

    public List<ValRekAdmin> getData() {
        return data;
    }

    public void setData(List<ValRekAdmin> data) {
        this.data = data;
    }
}
