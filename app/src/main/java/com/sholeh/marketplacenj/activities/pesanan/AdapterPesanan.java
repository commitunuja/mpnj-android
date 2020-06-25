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
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return TabSemua.newInstance(position + 1);
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