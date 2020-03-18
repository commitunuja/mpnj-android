package com.sholeh.marketplacenj.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.Foto;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Foto> tampil;

    public ViewPagerAdapter(Context context, ArrayList<Foto> tampil) {
        this.context = context;
        this.tampil = tampil;
    }

    public ViewPagerAdapter(ArrayList<Foto> tampil) {
        this.tampil = tampil;

    }

    @Override
    public int getCount() {
        return tampil.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_product_details, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgproduk);
//        imageView.setImageResource(images[position]);
        Glide.with(context)
                .load(CONSTANTS.SUB_DOMAIN + tampil.get(position).getFotoProduk())
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
