package com.sholeh.marketplacenj.respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValBank {
    @SerializedName("id_bank")
    @Expose
    private String idBank;
    @SerializedName("nama_bank")
    @Expose
    private String namaBank;

    @SerializedName("rekening")
    @Expose
    private String rekening;
    @SerializedName("atas_nama")
    @Expose
    private String atasNama;
    @SerializedName("foto_bank")
    @Expose
    private String fotoBank;

    public String getIdBank() {
        return idBank;
    }

    public void setIdBank(String idBank) {
        this.idBank = idBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getRekening() {
        return rekening;
    }

    public void setRekening(String rekening) {
        this.rekening = rekening;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }
    public String getFotoBank() {
        return fotoBank;
    }

    public void setFotoBank(String fotoBank) {
        this.fotoBank = fotoBank;
    }
}
