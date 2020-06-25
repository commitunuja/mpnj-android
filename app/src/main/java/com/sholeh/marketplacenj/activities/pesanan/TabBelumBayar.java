package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.dashboard.RecycleAdapteHomeBanner;
import com.sholeh.marketplacenj.adapter.pesanan.RecyclerPesananAdapter;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;

import java.util.ArrayList;


public class TabBelumBayar extends Fragment {

    private String TAG ="tabBelumBayar";
    private RecyclerView recycler_belumBayar;
    private TextView cart_count, continuebtn;
    public  TextView cart_totalamt;
    private RecyclerPesananAdapter pesananAdapter;
    public static TabBelumBayar newInstance(int index) {
        TabBelumBayar fragment = new TabBelumBayar();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    private ArrayList<PesananModel> pesananModels ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tab_belumbayar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//
//        recycler_belumBayar = view.findViewById(R.id.recycler_pesanan);
//        LinearLayoutManager mLayoutManger3 = new LinearLayoutManager( getActivity(), LinearLayoutManager.VERTICAL, false);
//        recycler_belumBayar.setLayoutManager(mLayoutManger3);
//        recycler_belumBayar.setItemAnimator(new DefaultItemAnimator());
//
//        pesananAdapter = new AdapterPesanan(this,pesananModels);
//        recycler_belumBayar.setAdapter(pesananAdapter);
    }

}