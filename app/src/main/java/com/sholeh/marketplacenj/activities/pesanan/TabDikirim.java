package com.sholeh.marketplacenj.activities.pesanan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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


public class TabDikirim extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RecyclerPesananAdapter recyclerPesananAdapter;
    private RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    LinearLayout linearLayout;

    private List<PesananModel> pesananModels;

    public static TabDikirim newInstance(int index) {
        TabDikirim fragment = new TabDikirim();
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
        linearLayout = view.findViewById(R.id.ldatakosong);

        getData();
        return view;
    }

    public void getData() {
        Preferences preferences = new Preferences(getActivity());
        String id_konsumen = preferences.getIdKonsumen();

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<List<PesananModel>> call = service.getDataPesanan(String.valueOf(id_konsumen), "dikirim");

        call.enqueue(new Callback<List<PesananModel>>() {
            @Override
            public void onResponse(Call<List<PesananModel>> call, Response<List<PesananModel>> response) {
                if (response.body().size() > 0 && response.isSuccessful()) {
                    pesananModels = response.body();
                    recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pesananModels);
                    recyclerView.setAdapter(recyclerPesananAdapter);

                } else {

                    recyclerView.setVisibility(View.INVISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<PesananModel>> call, Throwable t) {

            }
        });

    }
}