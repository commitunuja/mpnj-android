package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.mfragment.pesanan.PesananFragment;
import com.sholeh.marketplacenj.mfragment.pesanan.TabBelumBayar;
import com.sholeh.marketplacenj.mfragment.pesanan.TabDikirim;
import com.sholeh.marketplacenj.mfragment.pesanan.TabSelesai;
import com.sholeh.marketplacenj.mfragment.pesanan.Tabdibatalkan;
import com.sholeh.marketplacenj.mfragment.pesanan.Tabdikemas;
import com.sholeh.marketplacenj.model.pesanan.Pesanan;

public class AdapterPesanan extends FragmentPagerAdapter {
    Context mContext;
    Bundle bundle = new Bundle();

    PesananFragment pesananFragment = new PesananFragment().newInstance(1);
    private static final int[] TAB_TITLES = new int[]{R.string.semua, R.string.belum, R.string.dikemas, R.string.dikirim, R.string.selesai, R.string.batal};

    public AdapterPesanan(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                pesananFragment = new PesananFragment();
//                bundle.putString("status", pending);
//                pesananFragment.setArguments(bundle);
//                return pesananFragment;
                return PesananFragment.newInstance(position + 1);
            case 1:
                return TabBelumBayar.newInstance(position + 2);
            case 2:
                return Tabdikemas.newInstance(position + 3);
            case 3:
                return TabDikirim.newInstance(position + 4);
            case 4:
                return TabSelesai.newInstance(position + 5);
            case 5:
              return Tabdibatalkan.newInstance(position + 6);
            default:
                return null;

        }


    }


    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }


}