package com.sholeh.marketplacenj.mfragment.pesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.pesanan.RecyclerPesananAdapter;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PesananFragment extends Fragment {

    private List<DataPesanan> pesanan_models;
    RecyclerView recyclerView;
    RecyclerPesananAdapter recyclerPesananAdapter;

    String pending, verifikasi, packing, dikirim, sukses, batal;
    RecyclerView.LayoutManager dataapi;
//    public PesananFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);
        recyclerView = view.findViewById(R.id.recycler_pesanan);


        getData();
        return view;
    }

    public void getData() {


        dataapi = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pesanan_models);
        recyclerView.setLayoutManager(dataapi);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<DataPesanan>> call = service.getDataPesanan(String.valueOf(1), pending);
//
//        call.enqueue(new Callback<List<DataPesanan>>() {
//            @Override
//            public void onResponse(Call<List<DataPesanan>> call, Response<List<DataPesanan>> response) {
//                if (response.body() != null && response.isSuccessful()) {
//
//                    pesanan_models = response.body();
//                    recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pesanan_models);
//                    recyclerView.setAdapter(recyclerPesananAdapter);
//
//                    Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "gagal", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<DataPesanan>> call, Throwable t) {
//
//            }
//        });

    }
}