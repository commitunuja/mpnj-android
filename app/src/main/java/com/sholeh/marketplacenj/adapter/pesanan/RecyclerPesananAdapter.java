package com.sholeh.marketplacenj.adapter.pesanan;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.pesanan.DetailPesananActivity;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.model.pesanan.Pembayaran;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;
import com.sholeh.marketplacenj.util.CONSTANTS;

import java.util.List;

import static android.view.View.GONE;


public class RecyclerPesananAdapter extends RecyclerView.Adapter<RecyclerPesananAdapter.ViewHolder> {
    private Context context;
    private List<Pembayaran> pembayarans; // model / item
    private Pembayaran pesananModel;
    String tab = "batal";

    public RecyclerPesananAdapter(Context context, List<Pembayaran> pesananModels) {
        this.context = context;
        this.pembayarans = pesananModels;


    }


    @NonNull
    @Override
    public RecyclerPesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_pesananku, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPesananAdapter.ViewHolder viewHolder, int i) {
        pesananModel = pembayarans.get(i);
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<List<PesananModel>> call = service.getDataPesanan(String.valueOf(2), tab);
        Log.d("CEKLAGI", pesananModel.getDataPesanan().get(i).getNamaToko());
        viewHolder.namaProduk.setText(pesananModel.getDataPesanan().get(0).getItem().get(0).getNamaProduk());
        viewHolder.hargaProduk.setText("Rp " + pesananModel.getDataPesanan().get(0).getItem().get(0).getHargaJual());
        viewHolder.namatoko.setText(pesananModel.getDataPesanan().get(0).getNamaToko());
        viewHolder.totalbayar.setText("Rp " + pesananModel.getDataPesanan().get(0).getTotalPembayaran());
        viewHolder.jumlah.setText(pesananModel.getDataPesanan().get(0).getJumlahPesanan());
        viewHolder.status.setText(pesananModel.getDataPesanan().get(0).getItem().get(0).getStatusOrder());
//        if (pesananModel.getStatusPembayaran().equalsIgnoreCase("terima")) {
//            viewHolder.lbayar.setVisibility(GONE);
//        } else if (pesananModel.getStatus_order().equalsIgnoreCase("Dibatalkan")) {
//            viewHolder.lbayar.setVisibility(GONE);
//        } else {
//            viewHolder.batas.setText(pesananModel.getBatasBayar());
//        }


//        Glide.with(context)
//                .load(CONSTANTS.SUB_DOMAIN + pesananModel.getDataPesanan().get(0).getItem().get(0).getFoto())
//                .into(viewHolder.foto_produk);


    }

    @Override
    public int getItemCount() {
        return this.pembayarans.size();
    }
//
//    public void setFilter(List<Pembayaran> filterdata) {
//        this.pesananModels = filterdata;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, status, namatoko, totalbayar, jumlah, batas;
        private ImageView foto_produk;
        private CardView cardViewpesanan;
        private LinearLayout lbayar;


        public ViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.tvxNamaProduk);
            hargaProduk = itemView.findViewById(R.id.tvxHargaProd);
            namatoko = itemView.findViewById(R.id.tvxNamaToko);
            status = itemView.findViewById(R.id.tvx_statusbayar);
            totalbayar = itemView.findViewById(R.id.tvxTotalbayar);
            jumlah = itemView.findViewById(R.id.tvxjumProd);
//            batas = itemView.findViewById(R.id.tvxBatasbayar);
            foto_produk = itemView.findViewById(R.id.img_foto);
//            lbayar = itemView.findViewById(R.id.linearbayar);
            cardViewpesanan = itemView.findViewById(R.id.card_pesanan);

            cardViewpesanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailPesananActivity.class);
                    i.putExtra("namaproduk", pesananModel.getDataPesanan().get(0).getItem().get(0).getNamaProduk());
//                    i.putExtra("namatoko", pesananModel.getNamaToko());
                    i.putExtra("harga", pesananModel.getDataPesanan().get(0).getItem().get(0).getHargaJual());
                    i.putExtra("foto", pesananModel.getDataPesanan().get(0).getItem().get(0).getFoto());
                    context.startActivity(i);
                }
            });


        }
    }
}