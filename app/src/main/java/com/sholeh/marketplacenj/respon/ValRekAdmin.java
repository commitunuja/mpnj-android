package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValRekAdmin {
    @SerializedName("id_rekening_admin")
    @Expose
    private String idRekeningAdmin;
    @SerializedName("nomor_rekening")
    @Expose
    private String nomorRekening;
    @SerializedName("atas_nama")
    @Expose
    private String atasNama;

    public String getIdRekeningAdmin() {
        return idRekeningAdmin;
    }

    public void setIdRekeningAdmin(String idRekeningAdmin) {
        this.idRekeningAdmin = idRekeningAdmin;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }
}
