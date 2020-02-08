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


public class TabFragment1 extends Fragment implements View.OnClickListener {

    TextView tvx_logout;
    SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttab1,container,false);
        preferences = this.getActivity().getSharedPreferences("App", Context.MODE_PRIVATE);

        tvx_logout = rootView.findViewById(R.id.tvLogout);
        tvx_logout.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        logout();
    }

    public void logout(){
        tvx_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor proses = preferences.edit();
                proses.clear().commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }
}