package com.sholeh.marketplacenj.mfragment.details;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sholeh.marketplacenj.R;


/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class Viewpager_product_details2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewpager_product_details2,container,false);
        return view;


    }
}
