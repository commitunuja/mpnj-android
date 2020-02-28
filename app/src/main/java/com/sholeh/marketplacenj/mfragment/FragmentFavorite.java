package com.sholeh.marketplacenj.mfragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sholeh.marketplacenj.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorite extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite,container,false);

        return rootView;
    }


}
