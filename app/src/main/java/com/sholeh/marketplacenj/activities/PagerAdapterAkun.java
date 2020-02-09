package com.sholeh.marketplacenj.activities;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterAkun extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterAkun(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragmentPembeli tab1 = new TabFragmentPembeli();
                return tab1;
            case 1:
                TabFragmentPelapak tab2 = new TabFragmentPelapak();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}