package com.sholeh.marketplacenj.adapter.pesanan;

import android.content.Context;
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
import com.sholeh.marketplacenj.model.pesanan.detailpesanan.ItemDetailPesanan;
import com.sholeh.marketplacenj.util.CONSTANTS;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class DetailPesananAdapter extends RecyclerView.Adapter<DetailPesananAdapter.ViewHolder> {
    Context context;
    List<ItemDetailPesanan> dataPesanans;
    ItemDetailPesanan dataPesanan;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer stringTokenizer;


    public DetailPesananAdapter(Context context, List<ItemDetailPesanan> dataPesanans) {
        this.context = context;
        this.dataPesanans = dataPesanans;
    }



    @NonNull
    @Override
    public DetailPesananAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_detail_pesanan, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        dataPesanan = dataPesanans.get(position);
//        stringTokenizer = new StringTokenizer(formatRupiah.format(dataPesanan.getHarga()),",");
//        String hargabarang = stringTokenizer.nextToken().trim();
        int harga1 = Integer.parseInt(dataPesanan.getHarga());
        holder.jumlahproduk.setText("" + dataPesanan.getJumlah());
        holder.namaproduk.setText(dataPesanan.getNamaProduk());
        stringTokenizer = new StringTokenizer(formatRupiah.format(harga1), ",");
        String hargabarang = stringTokenizer.nextToken().trim();
        holder.harga.setText(hargabarang);

        holder.kode.valueOf(dataPesanan.getIdTransaksiDetail());
        int a = Integer.parseInt(String.valueOf(harga1));
        int b = Integer.parseInt(dataPesanan.getJumlah());
        int c = (a*b);
        stringTokenizer = new StringTokenizer(formatRupiah.format(c),",");
        String totalbayar = stringTokenizer.nextToken().trim();
        holder.totalbayarproduk.setText(totalbayar);



        Glide.with(context)
                .load(CONSTANTS.SUB_DOMAIN + dataPesanan.getFotoProduk())
                .into(holder.fotoproduk);
    }

    @Override
    public int getItemCount() {
        return this.dataPesanans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namatoko, statusorder, namaproduk, harga, totalbayar, jumlahproduk, totalbayarproduk;
        ImageView fotoproduk;
        String kode;
        CardView todetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaproduk = itemView.findViewById(R.id.tvxNamaProduk);
//            namatoko = itemView.findViewById(R.id.tvxNamaToko);
//            statusorder = itemView.findViewById(R.id.tv_status_order);
            harga = itemView.findViewById(R.id.tvxHargaProd);
//            totalbayar = itemView.findViewById(R.id.tv_total_bayar_pesanan);
            jumlahproduk = itemView.findViewById(R.id.tvxjumProd);
            fotoproduk = itemView.findViewById(R.id.img_foto_pesanan);
            todetail = itemView.findViewById(R.id.card_pesanan);
            totalbayarproduk = itemView.findViewById(R.id.tv_totalhargaproduk);


//            todetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(context, DetailPesananActivity.class);
//                    i.putExtra("kode", kode);
//                    context.startActivity(i);
//                }
//            });

        }
    }

}
