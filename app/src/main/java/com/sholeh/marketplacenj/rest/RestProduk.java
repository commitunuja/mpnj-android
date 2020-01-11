package com.sholeh.marketplacenj.rest;
import com.google.gson.annotations.SerializedName;
import com.sholeh.marketplacenj.model.AllProductModel;

import java.util.List;



//value
public class RestProduk { // untuk respon dari phpnya
    @SerializedName("data")
    String status;
    @SerializedName("result")
    List<AllProductModel> listDataproduk;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<AllProductModel> getListDataproduk() {
        return listDataproduk;
    }
    public void setListDataproduk(List<AllProductModel> listDataproduk) {
        this.listDataproduk= listDataproduk;
    }

}
