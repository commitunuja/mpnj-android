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


public class TabFragmentPembeli extends Fragment implements View.OnClickListener {

    TextView tvx_logout, tvx_SettingAkun;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);
        preferences = this.getActivity().getSharedPreferences("App", Context.MODE_PRIVATE);

        tvx_logout = rootView.findViewById(R.id.tvLogout);
        tvx_logout.setOnClickListener(this);
        tvx_SettingAkun = rootView.findViewById(R.id.tvSetting);
        tvx_SettingAkun.setOnClickListener(this);

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


            default:
                break;
        }


    }

    public void logout() {
        SharedPreferences.Editor proses = preferences.edit();
        proses.clear().commit();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);

    }

    public void SettingAkun() {
        Intent intent = new Intent(getActivity(), PengaturanAkun.class);
        getActivity().startActivity(intent);
       // getActivity().finish();

    }


}