package com.sholeh.marketplacenj.respon;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResBanner {
    @SerializedName("data")
    @Expose
    private List<ValBanner> data = null;

    public List<ValBanner> getData() {
        return data;
    }

    public void setData(List<ValBanner> data) {
        this.data = data;
    }

}
