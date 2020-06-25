package com.sholeh.marketplacenj.adapter.pesanan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.pesanan.PesananModel;

import java.util.List;


public class RecyclerPesananAdapter extends RecyclerView.Adapter<RecyclerPesananAdapter.ViewHolder> {
    private Context context;
    private List<PesananModel> pesananModels; // model / item
    private PesananModel pesananModel;


    public RecyclerPesananAdapter(Context context, List<PesananModel> pesananModels) {
        this.context = context;
        this.pesananModels = pesananModels;


    }


    @NonNull
    @Override
    public RecyclerPesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_pesananku, viewGroup, false);
        return new RecyclerPesananAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPesananAdapter.ViewHolder viewHolder, int i) {
        pesananModel = pesananModels.get(i);
        viewHolder.namaProduk.setText(pesananModel.getNamaProduk());
        viewHolder.hargaProduk.setText("Rp " + pesananModel.getHarga());
        viewHolder.status.setText(pesananModel.getStatusPembayaran());
        viewHolder.namatoko.setText(pesananModel.getNamaToko());
        viewHolder.totalbayar.setText("Rp "+pesananModel.getTotalBayar());
        viewHolder.jumlah.setText(pesananModel.getJumlah());
        viewHolder.batas.setText(pesananModel.getBatasBayar());

        Glide.with(context)
                .load(CONSTANTS.SUB_DOMAIN + pesananModel.getFoto_produk())
                .into(viewHolder.foto_produk);


    }

    @Override
    public int getItemCount() {
        return pesananModels.size();
    }

    public void setFilter(List<PesananModel> filterdata) {
        this.pesananModels = filterdata;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, status, namatoko, totalbayar, jumlah, batas;
        private ImageView foto_produk;


        public ViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.tvxNamaProduk);
            hargaProduk = itemView.findViewById(R.id.tvxHargaProd);
            namatoko = itemView.findViewById(R.id.tvxNamaToko);
            status = itemView.findViewById(R.id.tvx_statusbayar);
            totalbayar = itemView.findViewById(R.id.tvxTotalbayar);
            jumlah = itemView.findViewById(R.id.tvxjumProd);
            batas = itemView.findViewById(R.id.tvxBatasbayar);
            foto_produk = itemView.findViewById(R.id.img_foto);

        }
    }
}