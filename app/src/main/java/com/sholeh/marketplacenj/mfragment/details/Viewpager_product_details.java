package com.sholeh.marketplacenj.mfragment.details;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sholeh.marketplacenj.R;


/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class Viewpager_product_details extends Fragment {
    String namaproduk, urltoimage, vdeskripsi, vid_produk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_product_details, container, false);

        final ImageView foto = view.findViewById(R.id.imgproduk);

//        JSONArray array = new JSONArray();



//            vid_produk = getActivity().getIntent().getStringExtra("id_produk");
//            String.valueOf(vid_produk);
////        namaproduk = getActivity().getIntent().getExtras().getString("nama_produk");
//            urltoimage = getActivity().getIntent().getExtras().getString("foto_produk");
//
////            Glide.with(getContext()).load(urltoimage).into(foto);
            return view;


        }

//        return view;
//    }
}
