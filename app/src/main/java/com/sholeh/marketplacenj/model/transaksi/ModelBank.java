package com.sholeh.marketplacenj.model.transaksi;

public class ModelBank {
    String idBank, namaBank, noRekening, atasNama, fotoBank;

    public ModelBank(String idBank, String namaBank, String noRekening, String atasNama, String fotoBank) {
        this.idBank = idBank;
        this.namaBank = namaBank;
        this.noRekening = noRekening;
        this.atasNama = atasNama;
        this.fotoBank = fotoBank;
    }


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

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
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

    //    String paymentType;
//    Integer logoImage;
//
//    public ModelBank(String paymentType, Integer logoImage) {
//        this.paymentType = paymentType;
//        this.logoImage = logoImage;
//    }
//
//    public String getPaymentType() {
//        return paymentType;
//    }
//
//    public void setPaymentType(String paymentType) {
//        this.paymentType = paymentType;
//    }
//
//    public Integer getLogoImage() {
//        return logoImage;
//    }
//
//    public void setLogoImage(Integer logoImage) {
//        this.logoImage = logoImage;
//    }
}
