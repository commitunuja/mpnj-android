package com.sholeh.marketplacenj.adapter.details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sholeh.marketplacenj.mfragment.details.Viewpager_product_details;

/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class ViewpagerProductDetailsAdapter extends FragmentStatePagerAdapter {

    public ViewpagerProductDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Viewpager_product_details tab1 = new Viewpager_product_details();
                return tab1;

            case 1:
                Viewpager_product_details tab2 = new Viewpager_product_details();

                return tab2;

            case 2:
                Viewpager_product_details tab3 = new Viewpager_product_details();
                return tab3;

            case 3:
                Viewpager_product_details tab4 = new Viewpager_product_details();
                return tab4;
          /*      Viewpager_product_details2 tab2 = new Viewpager_product_details2();

                return tab2;

            case 2:
                Viewpager_product_details3 tab3 = new Viewpager_product_details3();
                return tab3;

            case 3:
                Viewpager_product_details4 tab4 = new Viewpager_product_details4();
                return tab4;*/


            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
