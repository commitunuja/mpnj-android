package com.sholeh.marketplacenj.activities.pesanan;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sholeh.marketplacenj.adapter.pesanan.RecyclerPesananAdapter;
import com.sholeh.marketplacenj.mfragment.pesanan.PesananFragment;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterPesanan extends FragmentPagerAdapter {
    List<Fragment> fragmentList = new ArrayList<>();
    List <String> fragmentTitle = new ArrayList<>();

    public AdapterPesanan(FragmentManager fm, int i) {

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