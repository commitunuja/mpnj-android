package com.sholeh.marketplacenj.model.keranjang;

import java.util.List;

public class FirstModel {
    private boolean isCheck;
    private String title;
    private List<HeaderModel> listSecondModel;

    public FirstModel() {
    }

    public boolean isCheck() {

        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public List<HeaderModel> getListSecondModel() {
        return listSecondModel;
    }

    public void setListSecondModel(List<HeaderModel> listSecondModel) {
        this.listSecondModel = listSecondModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FirstModel(boolean isCheck, String title, List<HeaderModel> listSecondModel) {

        this.isCheck = isCheck;
        this.title = title;
        this.listSecondModel = listSecondModel;
    }
}
