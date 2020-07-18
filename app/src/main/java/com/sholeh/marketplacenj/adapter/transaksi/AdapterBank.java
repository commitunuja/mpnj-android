package com.sholeh.marketplacenj.adapter.transaksi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.adapter.AlamatAdapter;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.model.transaksi.ModelBank;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class AdapterBank extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ModelBank> bankmodel;
    int myPos = 0;
    private int lastSelectedPosition = 0;
    boolean check = false;
    RadioButton btn;

    public AdapterBank(Context context, List<ModelBank>bankModel){
        this.bankmodel = bankModel;
        this.mContext = context;
    }

    private class BankItemView extends RecyclerView.ViewHolder {
        TextView paymentType;
        ImageView radioButton2,logoImage;
        LinearLayout viewline;
        RadioButton btn;

        public BankItemView(View view) {
            super(view);
            paymentType = (TextView) view.findViewById(R.id.paymentType);
            logoImage=(ImageView)view.findViewById(R.id.logoImage);
            viewline = (LinearLayout)view.findViewById(R.id.viewline);
            btn = view.findViewById(R.id.buttonBank);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pembayaran, parent,false);

        return new BankItemView(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final ModelBank model =  bankmodel.get(position);
        String idBank = model.getIdBank();
        ((BankItemView) holder).paymentType.setText(model.getNamaBank());
        Picasso.with(getContext()).load(CONSTANTS.ASSETBANK+model.getFotoBank()).into(((BankItemView) holder).logoImage);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = position;
//                Toast.makeText(mContext, ""+idBank, Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                Intent intent = new Intent("custom-pesan");
                intent.putExtra("idbank", String.valueOf(idBank));
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        });

        if (lastSelectedPosition == position) {
            ((BankItemView) holder).btn.setChecked(true);
            ((BankItemView) holder).btn.setEnabled(false);
        }
        else {
            ((BankItemView) holder).btn.setChecked(false);
            ((BankItemView) holder).btn.setEnabled(false);
        }

        if(position==1){
            ((BankItemView) holder).viewline.setVisibility(View.GONE);
        }else {
            ((BankItemView) holder).viewline.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return bankmodel.size();
    }


//
//    public AdapterBank(Context context, List<ModelBank> moviesList) {
//        this.moviesList = moviesList;
//        this.context = context;
//    }


//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//
//        TextView paymentType;
//        ImageView radioButton2,logoImage;
//        LinearLayout viewline;
//        RadioButton btn;
//
//
//
//        public MyViewHolder(View view) {
//            super(view);
//
//            paymentType = (TextView) view.findViewById(R.id.paymentType);
//            logoImage=(ImageView)view.findViewById(R.id.logoImage);
//            viewline = (LinearLayout)view.findViewById(R.id.viewline);
//            btn = view.findViewById(R.id.buttonBank);
//
//
//        }
//
//    }
//
//
//
//
//    @Override
//    public AdapterBank.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.custom_pembayaran, parent, false);
//
//
//        return new AdapterBank.MyViewHolder(itemView);
//
//
//    }
//
//
//
//    @Override
//    public void onBindViewHolder(final AdapterBank.MyViewHolder holder, final int position) {
//        ModelBank movie = moviesList.get(position);
//        holder.paymentType.setText(movie.getPaymentType());
//        holder.logoImage.setImageResource((movie.getLogoImage()));
//
////        holder.btn.setChecked(lastSelectedPosition == position);
//
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "klik", Toast.LENGTH_SHORT).show();
//                lastSelectedPosition = position;
//                notifyDataSetChanged();
//            }
//        });
//
//        if (lastSelectedPosition == position) {
//            holder.btn.setChecked(true);
//            holder.btn.setEnabled(false);
//        }
//        else {
//            holder.btn.setChecked(false);
//            holder.btn.setEnabled(false);
//        }
//
//
//        if(position==3){
//
//            holder.viewline.setVisibility(View.GONE);
//        }else {
//            holder.viewline.setVisibility(View.VISIBLE);
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return moviesList.size();
//    }


}
