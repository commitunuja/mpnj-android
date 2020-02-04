package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResLogin {  // respon login konsumen
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("token")
    @Expose
    private String token;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public class Information {
        @SerializedName("username")
        @Expose
        private String username;


        public String getUsername() {
            return username;
        }
    }

}
