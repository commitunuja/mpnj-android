package com.sholeh.marketplacenj.adapter.pesanan;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerPesananAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<PesananModel> pesanan_models;
    private Context mContext;
    private String TAG ="cartAdapter";
    public RecyclerPesananAdapter(Context context, List<PesananModel> pesananModels){
        this.pesanan_models = pesananModels;
        this.mContext = context;
    }

    private class cartItemView extends RecyclerView.ViewHolder {
        TextView tvxIdTransaksi, tvxNamaToko, tvxStatusPembayaran, tvxNamaProduk, tvxJumlah, tvxharga, tvxTotalBayar, tvxBatasBayar;
        CardView cardView;


        public cartItemView(View itemView) {
            super(itemView);
            tvxIdTransaksi = (TextView) itemView.findViewById(R.id.idtransaksi);
            tvxNamaToko = (TextView) itemView.findViewById(R.id.tvxNamaToko);
            tvxStatusPembayaran = (TextView) itemView.findViewById(R.id.tvx_statusbayar);
            tvxNamaProduk = (TextView) itemView.findViewById(R.id.tvxNamaProduk);
            tvxJumlah = (TextView) itemView.findViewById(R.id.tvxjumProd);
            tvxharga = (TextView) itemView.findViewById(R.id.tvxHargaProd);
            tvxTotalBayar = (TextView) itemView.findViewById(R.id.tvxTotalbayar);
            tvxBatasBayar = (TextView) itemView.findViewById(R.id.tvxBatasbayar);
            cardView = (CardView) itemView.findViewById(R.id.card_pesanan);


        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pesananku, parent,false);
        Log.e(TAG, "  view created ");
        return new cartItemView(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final PesananModel model =  pesanan_models.get(position);

        ((cartItemView) holder).tvxIdTransaksi.setText(model.getIdTransaksi());
        ((cartItemView) holder).tvxNamaToko.setText(model.getNamaToko());
        ((cartItemView) holder).tvxStatusPembayaran.setText(model.getStatusPembayaran());
        ((cartItemView) holder).tvxNamaProduk.setText(model.getNamaProduk());
        ((cartItemView) holder).tvxJumlah.setText(model.getJumlah());
        ((cartItemView) holder).tvxTotalBayar.setText(model.getTotalBayar());
        ((cartItemView) holder).tvxBatasBayar.setText(model.getBatasBayar());

//        Glide.with(mContext)
//                .load(Constant.tampilfoto+model.getImg_ulr())
//                .into(((cartItemView) holder).prod_img);


//        ((cartItemView) holder).add_to_wishlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addtowishlist( model.getProd_id());
//            }
//        });
//
//        ((cartItemView) holder).prod_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ProdukDetailActivity.class);
//                intent.putExtra("prod_id", model.getProd_id());
//                mContext.startActivity(intent);
//            }
//        });

//        ((cartItemView) holder).prod_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ProdukDetailActivity.class);
//                intent.putExtra("prod_id", model.getProd_id());
//                mContext.startActivity(intent);
//            }
//        });

        // delete product from cart
//        ((cartItemView) holder).prod_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteProduct(model.getProd_id());
//            }
//        });

//        ((cartItemView) holder).prod_qty.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//
//                if (i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_GO ||i == EditorInfo.IME_ACTION_SEND ){
//
//                    if (!((cartItemView) holder).prod_qty.getText().toString().trim().equals("") || !((cartItemView) holder).prod_qty.getText().toString().trim().equals("0")){
//
//                        updateCartQty( model.getProd_id(), ((cartItemView) holder).prod_qty.getText().toString().trim());
//
//                    }
//
//
//                }
//
//
//                return false;
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return pesanan_models.size();
    }









}

