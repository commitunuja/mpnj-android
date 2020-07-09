package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.pesanan.PesananAdapter;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.List;


public class TabSelesai extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private LinearLayoutManager linearLayoutManager;
    private PesananAdapter recyclerPesananAdapter;
    private RecyclerView recyclerView;
    private List<DataPesanan> pembayarans;
    LinearLayout linearLayout;
    String tab = "sukses";

    public static TabSelesai newInstance(int index) {
        TabSelesai fragment = new TabSelesai();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_pesanan);

        getData();
    }

    public void getData() {
        Preferences preferences = new Preferences(getActivity());
        String id_konsumen = preferences.getIdKonsumen();

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
//        recyclerView.setAdapter( recyclerPesananAdapter );


//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<Pembayaran>> call = service.getDataPesanan(String.valueOf(id_konsumen));
//        call.enqueue(new Callback<List<Pembayaran>>() {
//            @Override
//            public void onResponse(Call<List<Pembayaran>> call, Response<List<Pembayaran>> response) {
//
//                if (response.body() != null && response.isSuccessful()) {
//                    Toast.makeText(getContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
//                    pembayarans = response.body();
//                    recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pembayarans);
//                    recyclerView.setAdapter(recyclerPesananAdapter);
//
//                } else {
//                    Toast.makeText(getContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Pembayaran>> call, Throwable t) {
//
//            }
//        });

    }
}