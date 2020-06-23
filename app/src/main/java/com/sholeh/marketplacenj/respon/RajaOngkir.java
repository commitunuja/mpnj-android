package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RajaOngkir {
    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("origin_details")
    @Expose
    private OriginDetails originDetails;
    @SerializedName("destination_details")
    @Expose
    private KecamatanDetail destinationDetails;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OriginDetails getOriginDetails() {
        return originDetails;
    }

    public void setOriginDetails(OriginDetails originDetails) {
        this.originDetails = originDetails;
    }

    public KecamatanDetail getDestinationDetails() {
        return destinationDetails;
    }

    public void setDestinationDetails(KecamatanDetail destinationDetails) {
        this.destinationDetails = destinationDetails;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
