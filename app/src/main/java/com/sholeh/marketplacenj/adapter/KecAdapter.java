package com.sholeh.marketplacenj.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.subdistrict.Result;

import java.util.ArrayList;
import java.util.List;

public class KecAdapter extends BaseAdapter {
    private Activity activitykec;
    private LayoutInflater inflaterkec;
    private List<Result> movieItemskec;
    private ArrayList<Result> listlokasiaslikec;



    public KecAdapter(Activity activitykec, List<Result> movieItemskec) {
        this.activitykec = activitykec;
        this.movieItemskec = movieItemskec;

        listlokasiaslikec = new ArrayList<Result>();
        listlokasiaslikec.addAll(movieItemskec);
    }

    @Override
    public int getCount() {
        return movieItemskec.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItemskec.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflaterkec == null)
            inflaterkec = (LayoutInflater) activitykec
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflaterkec.inflate(R.layout.custom_item_alamat, null);

        TextView tv_category = convertView.findViewById(R.id.tv_category);
//        TextView tv_detail =  convertView.findViewById(R.id.tv_detail);

        Result m = movieItemskec.get(position);

        tv_category.setText(m.getSubdistrictName());
//        tv_detail.setText(m.getCityId());

        return convertView;
    }

    @SuppressLint("DefaultLocale")
    public void filter(String charText)
    {
        charText = charText.toLowerCase();

        movieItemskec.clear();
        if (charText.length() == 0) {
            /* tampilkan seluruh data */
            movieItemskec.addAll(listlokasiaslikec);

        } else {
            for (Result lok : listlokasiaslikec) {
                if (lok.getSubdistrictName().toLowerCase().contains(charText)) {
                    movieItemskec.add(lok);
                } else {
                }

            }
        }

        notifyDataSetChanged();
    }

    public void setList(List<Result> movieItems){
        this.listlokasiaslikec.addAll(movieItems);
    }

}
