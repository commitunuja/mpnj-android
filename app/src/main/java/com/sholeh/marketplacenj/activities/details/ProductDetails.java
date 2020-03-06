package com.sholeh.marketplacenj.activities.details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.details.Details4TabAdapter;

public class ProductDetails extends AppCompatActivity {

    TextView search_mobile_text;

    TabLayout tab_layout;
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coco_product_details4);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        search_mobile_text = findViewById(R.id.search_mobile_text);
        search_mobile_text.setVisibility(View.GONE);





        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.addTab(tab_layout.newTab().setText("Details"));
        tab_layout.addTab(tab_layout.newTab().setText("Specifications"));
        tab_layout.addTab(tab_layout.newTab().setText("Reviews"));
//        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
//        ViewGroup vg = (ViewGroup) tab_layout.getChildAt(0);
//        int tabsCount = vg.getChildCount();
//        for (int j = 0; j < tabsCount; j++) {
//            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
//            int tabChildsCount = vgTab.getChildCount();
//            for (int i = 0; i < tabChildsCount; i++) {
//                View tabViewChild = vgTab.getChildAt(i);
//                if (tabViewChild instanceof TextView) {
//                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
//                }
//            }
//        }

        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager1 = (ViewPager) findViewById(R.id.pager);
        Details4TabAdapter adapter = new Details4TabAdapter(getSupportFragmentManager(), 3);
        viewPager1.setAdapter(adapter);
        viewPager1.setOffscreenPageLimit(1);
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
