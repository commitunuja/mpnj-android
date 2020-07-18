package com.sholeh.marketplacenj.adapter.bank;

public class itemspin {
    private String namabank;
    private int imagebank;
    public itemspin(String namaBank, int imageBank) {
        namabank = namaBank;
        imagebank = imageBank;
    }

    public String getNamabank() {
        return namabank;
    }

    public void setNamabank(String namabank) {
        this.namabank = namabank;
    }

    public int getImagebank() {
        return imagebank;
    }

    public void setImagebank(int imagebank) {
        this.imagebank = imagebank;
    }
}
