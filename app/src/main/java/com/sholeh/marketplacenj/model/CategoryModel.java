package com.sholeh.marketplacenj.model;



public class CategoryModel {
    public int catid, icres;
    public String title;
    public boolean isBig;

    public CategoryModel(int catid, int icres, String title, boolean isBig) {
        this.catid = catid;
        this.icres = icres;
        this.title = title;
        this.isBig = isBig;
    }
}
