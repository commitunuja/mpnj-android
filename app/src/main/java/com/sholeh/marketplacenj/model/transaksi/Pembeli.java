package com.sholeh.marketplacenj.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pembeli {

    @SerializedName("pembeli_id")
    @Expose
    private Integer pembeliId;
    @SerializedName("username")
    @Expose
    private String username;

    public Integer getPembeliId() {
        return pembeliId;
    }

    public void setPembeliId(Integer pembeliId) {
        this.pembeliId = pembeliId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}