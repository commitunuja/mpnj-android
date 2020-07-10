package com.sholeh.marketplacenj.respon;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sholeh.marketplacenj.model.wishlist.ValResWishllist;

public class ResTampilWishlist {
    @SerializedName("data")
    @Expose
    private List<ValResWishllist> data = null;

    public List<ValResWishllist> getData() {
        return data;
    }

    public void setData(List<ValResWishllist> data) {
        this.data = data;
    }

}
