package com.sholeh.marketplacenj.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sholeh.marketplacenj.respon.ValDataProfil;

public class Preferences {


    public static final boolean SP_Akses = true;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    private Context mContext;
    private static Preferences mInstance;


    public static final String SP_MPNJAPP = "spMpnjApp";
    public static final String SP_NAMA = "spNama";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static  final String SP_STATUS = "spStatus";
    public static  final String SP_Aktif = "spAktif";
    public static  final String SP_UserName = "spUserName";
    public static  final String SP_IdKonsumen = "spIdKonsumen";





    public Preferences(Context context){
        sp = context.getSharedPreferences(SP_MPNJAPP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
        this.mContext = context;
    }

    public static synchronized Preferences getInstance(Context context){

        if (mInstance == null){
            mInstance = new Preferences(context);
        }
        return mInstance;
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


    public void saveUser(ValDataProfil userProfil){

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_MPNJAPP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id_konsumen",userProfil.getIdKonsumen());
        editor.putString("nama_lengkap",userProfil.getNamaLengkap());
        editor.putString("email",userProfil.getEmail());
        editor.putString("nomor_hp",userProfil.getNomorHp());
        editor.putString("foto_profil",userProfil.getFotoProfil());
        editor.apply();

    }
//
    public ValDataProfil getProfil(){
        sp = mContext.getSharedPreferences(SP_MPNJAPP,Context.MODE_PRIVATE);
        return
                new ValDataProfil(
                        sp.getInt("id_konsumen",-1),
                        sp.getString("nama_lengkap",null),
                        sp.getString("email",null),
                        sp.getString("nomor_hp",null),
                        sp.getString("foto_profil",null)
                );
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
