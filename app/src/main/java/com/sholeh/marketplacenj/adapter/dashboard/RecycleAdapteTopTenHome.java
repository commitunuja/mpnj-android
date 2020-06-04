package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.dashboard.TopTenModelClass;

import java.util.List;

public class RecycleAdapteTopTenHome extends RecyclerView.Adapter<RecycleAdapteTopTenHome.MyViewHolder> {
    Context context;


    private List<TopTenModelClass> moviesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView title, harga, type;
        LinearLayout linear;


        public MyViewHolder(View view) {
            super(view);

            image = (ImageView) view.findViewById(R.id.imageproduk);
            title = (TextView) view.findViewById(R.id.titleproduk);
            harga = (TextView) view.findViewById(R.id.txthargaawal);
            type  = view.findViewById(R.id.typeproduk);

//            linear = (LinearLayout) view.findViewById(R.id.linear);

        }

    }


    public RecycleAdapteTopTenHome(Context context, List<TopTenModelClass> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_ten_list, parent, false);

        return new MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        TopTenModelClass movie = moviesList.get(position);
        holder.image.setImageResource(movie.getImage());
        holder.title.setText(movie.getTitle());
//        holder.harga.setText(movie.get());



    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}

