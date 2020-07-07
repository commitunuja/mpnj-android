package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.pesanan.RecyclerPesananAdapter;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.model.pesanan.Pembayaran;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabBelumBayar extends Fragment {

    SupportRequestManagerFragment supportRequestManagerFragment;
    private RecyclerPesananAdapter pesananAdapter;
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<Pembayaran> pembayarans;
    LinearLayout datakosong;
    String tab = "pending";

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


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<Pembayaran>> call = service.getDataPesanan(String.valueOf(id_konsumen));
        call.enqueue(new Callback<List<Pembayaran>>() {
            @Override
            public void onResponse(Call<List<Pembayaran>> call, Response<List<Pembayaran>> response) {

                if (response.body() != null && response.isSuccessful()) {
                    Toast.makeText(getContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
                    pembayarans = response.body();
                    pesananAdapter = new RecyclerPesananAdapter(getContext(), pembayarans);
                    recyclerView.setAdapter(pesananAdapter);

                } else {
                    Toast.makeText(getContext(), "" + response.body(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Pembayaran>> call, Throwable t) {

            }
        });

    }
}