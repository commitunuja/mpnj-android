package com.sholeh.marketplacenj.mfragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.activities.TabFragmentPelapak;
import com.sholeh.marketplacenj.activities.TabFragmentPembeli;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.util.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfil extends Fragment {

    private ImageView nav_home, nav_notifikasi, nav_transaksi, navprofile;
    TextView tvx_login, tvx_namaCustomter, tvx_logout;

    FloatingActionButton fb_favourite;
    Toolbar toolBarisi;

    Preferences preferences;
    String id_konsumen, username;
    private ResProfil tvDataProfil;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil,container,false);
        preferences = new Preferences(getActivity());
        id_konsumen = preferences.getIdKonsumen();

        tvx_namaCustomter = rootView.findViewById(R.id.tvCustomerName);


        TabLayout tabLayout =  rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("AKUN PEMBELI"));
        tabLayout.addTab(tabLayout.newTab().setText("AKUN PELAPAK"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = rootView.findViewById(R.id.pagerprofil);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        PagerAdapterAkun adapter = new PagerAdapterAkun(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
//        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getDataProfil();

        return rootView;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new TabFragmentPembeli();
                case 1:
                    return new TabFragmentPelapak();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void getDataProfil(){

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);

        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {

                tvDataProfil = response.body();
                String namaLengkap = tvDataProfil.getData().getNamaLengkap();
                tvx_namaCustomter.setText(namaLengkap);

            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                Toast.makeText(getActivity(), "no connection"+t, Toast.LENGTH_SHORT).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }
}
