package com.sholeh.marketplacenj.activities.pesanan;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sholeh.marketplacenj.R;

public class AdapterPesanan extends FragmentPagerAdapter {
    private final Context mContext;
    private static final int[] TAB_TITLES = new int[]{R.string.semua, R.string.belum, R.string.dikemas, R.string.dikirim, R.string.selesai, R.string.batal};

    public AdapterPesanan(Context context, FragmentManager fm) {

        super(fm);
    }

//    public AdapterPesanan(FragmentManager supportFragmentManager) {
//        super(fm);
//    }

//    public AdapterPesanan(FragmentManager supportFragmentManager) {
//        super();
//    }
//
//    public AdapterPesanan() {
//        super(fm);
//    }


    @Override
    public Fragment getItem(int position) {
        PesananFragment data1 = new PesananFragment();
        return data1;

//        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }
}