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

    private static final String ARG_SECTION_NUMBER = "123";
    private DataPesanan pesanan_models;
    private List<DataPesanan> dataPesanans;
    //    List<Pesanan> pesanans;
    RecyclerView recyclerView;
    RecyclerPesananAdapter recyclerPesananAdapter;
    private HashMap<DataPesanan, List<Item>> item;

    TextView nama;

    String pending, verifikasi, packing, dikirim, sukses, batal;
    RecyclerView.LayoutManager dataapi;

    public static PesananFragment newInstance(int index) {
        PesananFragment fragment = new PesananFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);
        recyclerView = view.findViewById(R.id.recycler_pesanan1);


        getData();
        return view;
    }

    public void getData() {
        String id_konsumen;
        Preferences preferences = new Preferences(getContext());
        id_konsumen = preferences.getIdKonsumen();
        dataapi = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerPesananAdapter = new RecyclerPesananAdapter(getContext(), pesanan_models);
        recyclerView.setLayoutManager(dataapi);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<Pesanan> call = service.getDataPesanan(String.valueOf(id_konsumen));

        dataPesanans = new ArrayList<>();
        item = new HashMap<>();
        call.enqueue(new Callback<Pesanan>() {
            @Override
            public void onResponse(Call<Pesanan> call, retrofit2.Response<Pesanan> response) {
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getDataPesanan().size() > 0) {
//                    recyclerView.setAdapter(recyclerPesananAdapter);
                        dataPesanans.clear();
                        item.clear();
                        List<DataPesanan> array = response.body().getDataPesanan();
                        for (int i = 0; i < array.size(); i++) {
//
                            dataPesanans.add(new DataPesanan(response.body().getDataPesanan().get(i).getNamaToko(),
                                    response.body().getDataPesanan().get(i).getJumlahPesanan(),
                                    response.body().getDataPesanan().get(i).getTotalPembayaran(),
                                    response.body().getDataPesanan().get(i).getKodeInvoice(),
                                    response.body().getDataPesanan().get(i).getWaktuTransaksi(),
                                    response.body().getDataPesanan().get(i).getItem()));
//            }
//        });
                            itemdata = new ArrayList<>();
                            List<Item> items = array.get(i).getItem();

    }
}