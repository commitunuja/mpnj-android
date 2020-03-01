package com.sholeh.marketplacenj.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String SP_MPNJAPP = "spMpnjApp";
    public static final String SP_NAMA = "spNama";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static  final String SP_STATUS = "spStatus";
    public static  final String SP_Aktif = "spAktif";
    public static  final String SP_UserName = "spUserName";
    public static  final String SP_IdKonsumen = "spIdKonsumen";
    
    public static final boolean SP_Akses = true;

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public Preferences(Context context){
        sp = context.getSharedPreferences(SP_MPNJAPP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpPassword(){
        return sp.getString(SP_PASSWORD, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public String getSPStatus(){return sp.getString(SP_STATUS,"");}

    public String getSPAktif(){return sp.getString(SP_Aktif,"");}

    public String getUsername(){return sp.getString(SP_UserName,"");}

    public String getIdKonsumen(){return sp.getString(SP_IdKonsumen,"");}



}
