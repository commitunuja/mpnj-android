package com.sholeh.marketplacenj.model.cost;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Query {
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("originType")
    @Expose
    private String originType;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("destinationType")
    @Expose
    private String destinationType;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("courier")
    @Expose
    private String courier;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(String destinationType) {
        this.destinationType = destinationType;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCourier() {
        return courier;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }
}
