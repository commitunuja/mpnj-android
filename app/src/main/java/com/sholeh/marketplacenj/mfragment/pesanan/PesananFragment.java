package com.sholeh.marketplacenj.mfragment.pesanan;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;

import java.util.List;


public class PesananFragment extends Fragment {

    private List<PesananModel> pesanan_models;
    private Context mContext;
    private String TAG ="cartAdapter";

    TextView tvxIdTransaksi, tvxNamaToko, tvxStatusPembayaran, tvxNamaProduk, tvxJumlah, tvxharga, tvxTotalBayar, tvxBatasBayar;
    CardView cardView;

//    public PesananFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);












        return view;
    }
}