package com.sholeh.marketplacenj.activities.pesanan;

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
import com.sholeh.marketplacenj.model.pesanan.PesananModel;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabBelumBayar extends Fragment {


    private RecyclerPesananAdapter pesananAdapter;
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<PesananModel> pesananModels;
    String pending;

    public static TabBelumBayar newInstance(int index) {
        TabBelumBayar fragment = new TabBelumBayar();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);
        recyclerView = view.findViewById(R.id.recycler_pesanan);

        getData();
        return view;
    }

    public void getData() {

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<PesananModel>> call = service.getDataPesanan(String.valueOf(2), "pending");

        call.enqueue(new Callback<List<PesananModel>>() {
            @Override
            public void onResponse(Call<List<PesananModel>> call, Response<List<PesananModel>> response) {
                if (response.body() != null && response.isSuccessful()) {

                    pesananModels = response.body();
                    pesananAdapter = new RecyclerPesananAdapter(getContext(), pesananModels);
                    recyclerView.setAdapter(pesananAdapter);

                } else {
                    Toast.makeText(getContext(), "gagal", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<PesananModel>> call, Throwable t) {

            }
        });

    }
}