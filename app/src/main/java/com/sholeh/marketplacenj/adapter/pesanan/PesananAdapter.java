package com.sholeh.marketplacenj.adapter.pesanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.pesanan.DetailPesananActivity;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.model.pesanan.Item;
import com.sholeh.marketplacenj.util.CONSTANTS;

import java.util.HashMap;
import java.util.List;


public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.ViewHolder> {
    Context context;
    List<DataPesanan> dataPesanans;
    DataPesanan dataPesanan;
    HashMap<DataPesanan, List<Item>> items;


    public PesananAdapter(Context context, List<DataPesanan> dataPesanans, HashMap<DataPesanan, List<Item>> item) {
        this.context = context;
        this.dataPesanans = dataPesanans;
        this.items = item;
    }

    @NonNull
    @Override
    public PesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_pesananku, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananAdapter.ViewHolder holder, int position) {

        dataPesanan = dataPesanans.get(position);

        holder.namatoko.setText(dataPesanan.getNamaToko());
        holder.totalbayar.setText("Rp " + dataPesanan.getTotalPembayaran());
        holder.jumlahproduk.setText("" + dataPesanan.getJumlahPesanan());
        holder.namaproduk.setText(dataPesanan.getItem().get(0).getNamaProduk());
        holder.harga.setText("Rp " + dataPesanan.getItem().get(0).getHargaJual());
        holder.statusorder.setText(dataPesanan.getItem().get(0).getStatusOrder());
        holder.kode = dataPesanan.getKodeInvoice();


        Glide.with(context)
                .load(CONSTANTS.SUB_DOMAIN + dataPesanan.getItem().get(0).getFoto())
                .into(holder.fotoproduk);
    }

    @Override
    public int getItemCount() {
        return this.dataPesanans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namatoko, statusorder, namaproduk, harga, totalbayar, jumlahproduk;
        ImageView fotoproduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaproduk = itemView.findViewById(R.id.tvxNamaProduk);
            namatoko = itemView.findViewById(R.id.tvxNamaToko);
            statusorder = itemView.findViewById(R.id.tv_status_order);
            harga = itemView.findViewById(R.id.tvxHargaProd);
            totalbayar = itemView.findViewById(R.id.tv_total_bayar_pesanan);
            jumlahproduk = itemView.findViewById(R.id.tvxjumProd);
            fotoproduk = itemView.findViewById(R.id.img_foto_pesanan);


        }
    }
}
