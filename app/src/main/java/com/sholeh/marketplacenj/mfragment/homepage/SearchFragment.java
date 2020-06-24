package com.sholeh.marketplacenj.mfragment.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sholeh.marketplacenj.R;

public class SearchFragment extends Fragment {

TextView coba;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        coba = v.findViewById(R.id.tvcoba);


        return v;
    }
}