package com.sholeh.marketplacenj.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sholeh.marketplacenj.respon.ValDataProfil;

public class Preferences {


    public static final boolean SP_Akses = true;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;


    public static final String SP_MPNJAPP = "spMpnjApp";
    public static final String SP_NAMA = "spNama";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static  final String SP_STATUS = "spStatus";
    public static  final String SP_Aktif = "spAktif";

    public static  final String SP_IdKonsumen = "spIdKonsumen";
    public static  final String SP_UserName = "spUserName";
    public static  final String SP_NamaLengkap = "spNamaLengkap";
    public static  final String SP_NomorHP = "spNomorHp";
    public static  final String SP_Email = "spEmail";
    public static  final String SP_FotoAkun = "spFotoAkun";






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

    public String getNamaLengkap(){return sp.getString(SP_NamaLengkap,"");}
    public String getNomorHp(){return sp.getString(SP_NomorHP,"");}
    public String getEmailnya(){return sp.getString(SP_Email,"");}
    public String getFotoAkun(){return sp.getString(SP_FotoAkun,"");}


}
