package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class TabSemua extends Fragment {
    private List<DataPesanan> pembayarans;
    RecyclerView recyclerView;
    PesananAdapter recyclerPesananAdapter;
    String tab = " ";
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static TabSemua newInstance(int index) {
        TabSemua fragment = new TabSemua();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);
//
//
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
        recyclerView.setAdapter( recyclerPesananAdapter );


//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<Pembayaran>> call = service.getDataPesanan(String.valueOf(id_konsumen));
//        call.enqueue(new Callback<List<Pembayaran>>() {
//            @Override
//            public void onResponse(Call<List<Pembayaran>> call, Response<List<Pembayaran>> response) {
//
//                if (response.body() != null && response.isSuccessful()) {
//                    if (response.body().get(0).getDataPesanan().size() > 0) {
//                        Toast.makeText(getContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
//                        pembayarans = response.body();
//                        recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pembayarans);
//                        recyclerView.setAdapter(recyclerPesananAdapter);
//
//                    } else {
//                        Toast.makeText(getContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Pembayaran>> call, Throwable t) {
//
//            }
//        });

    }
}