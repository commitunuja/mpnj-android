package com.sholeh.marketplacenj.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.Preferences;


public class TabFragmentPembeli extends Fragment implements View.OnClickListener {

    TextView tvx_logout, tvx_SettingAkun, tvx_Alamat, tvx_myprofil;
    Preferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);
        preferences = new Preferences(getActivity());

        tvx_logout = rootView.findViewById(R.id.tvLogout);
        tvx_Alamat = rootView.findViewById(R.id.tvAlamat);
        tvx_SettingAkun = rootView.findViewById(R.id.tvSetting);
        tvx_myprofil = rootView.findViewById(R.id.tv_myprofil);

        tvx_Alamat.setOnClickListener(this);
        tvx_myprofil.setOnClickListener(this);
        tvx_SettingAkun.setOnClickListener(this);
        tvx_logout.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogout:
                logout();
                break;

            case R.id.tvSetting:
                SettingAkun();
                break;

            case R.id.tvAlamat:
                Intent intent = new Intent(getActivity(), AlamatActivity.class);
                getActivity().startActivity(intent);
                break;

            case R.id.tv_myprofil:
                Intent i = new Intent(getActivity(), AkunActivity.class);
                getActivity().startActivity(i);
                break;


            default:
                break;
        }


    }

    public void logout() {
        preferences.saveSPBoolean(preferences.SP_SUDAH_LOGIN, false);

//        SharedPreferences.Editor proses = preferences.edit();
//        proses.clear().commit();
        startActivity(new Intent(getActivity(), LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
        Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_LONG).show();

    }

    public void SettingAkun() {
        Intent intent = new Intent(getActivity(), PengaturanAkun.class);
        getActivity().startActivity(intent);
       // getActivity().finish();

    }


}