package com.sholeh.marketplacenj.model.cost;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sholeh.marketplacenj.respon.ValCost;

import java.util.List;

public class Cost {
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cost")
    @Expose
    private List<Cost_> cost = null;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cost_> getCost() {
        return cost;
    }

    public void setCost(List<Cost_> cost) {
        this.cost = cost;
    }

}
