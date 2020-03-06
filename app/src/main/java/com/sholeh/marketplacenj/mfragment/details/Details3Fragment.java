package com.sholeh.marketplacenj.mfragment.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;

public class Details3Fragment extends Fragment {
    String namaproduk, urltoimage, vdeskripsi, vid_produk;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager, container, false);

        ImageView foto = view.findViewById(R.id.imgproduk);



        vid_produk = getActivity().getIntent().getStringExtra("id_produk");
        String.valueOf(vid_produk);
//        namaproduk = getActivity().getIntent().getExtras().getString("nama_produk");
        urltoimage = getActivity().getIntent().getExtras().getString("foto_produk");

        Glide.with(getContext()).load(urltoimage).into(foto);
      /*  vhargaproduk = Integer.parseInt(getIntent().getStringExtra("harga_jual"));
        vstok = Integer.parseInt(getIntent().getStringExtra("stok"));
        vterjual = Integer.parseInt(getIntent().getStringExtra("terjual"));
        vdeskripsi = getIntent().getExtras().getString("keterangan");*/

        return view;
    }
}
