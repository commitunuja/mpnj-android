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


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<PesananModel>> call = service.getDataPesanan(String.valueOf(1), "pending");

        call.enqueue(new Callback<List<PesananModel>>() {
            @Override
            public void onResponse(Call<List<PesananModel>> call, Response<List<PesananModel>> response) {
                if (response.body() != null && response.isSuccessful()) {

                    pesanan_models = response.body();
                    recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pesanan_models);
                    recyclerView.setAdapter(recyclerPesananAdapter);

                    Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
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