package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.image.SmartImageView;
import com.sholeh.marketplacenj.model.Foto;
import com.sholeh.marketplacenj.model.pesanan.DataPesanan;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.model.Model;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {
    private Context context;
    private List<Model> tvDataProduks; // model / item
    private Model tvDataProduk;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer stringTokenizer;


    public ProdukAdapter(Context context, List<Model> tvDataProduks) {
        this.context = context;
        this.tvDataProduks = tvDataProduks;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_ten_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        tvDataProduk = tvDataProduks.get(i);
        viewHolder.namaProduk.setText(tvDataProduk.getNamaProduk());
        viewHolder.tvxdiskon.setText(tvDataProduk.getDiskon()+"%");
        viewHolder.type.setText(tvDataProduk.getKategori().getNamaKategori());


        int hargaAwal = tvDataProduk.getHargaJual();
        double diskon = tvDataProduk.getDiskon();

        double h = diskon / 100 * hargaAwal;
        double p = hargaAwal - h;

        stringTokenizer = new StringTokenizer(formatRupiah.format(p), ",");
        String hargajum = stringTokenizer.nextToken().trim();
        viewHolder.hargaProduk.setText(hargajum);
        Picasso.with(context)
                .load(CONSTANTS.SUB_DOMAIN + tvDataProduk.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);

        if (tvDataProduk.getDiskon() == 0){
            viewHolder.relativeDiskon.setVisibility(View.GONE);
        }else{
            viewHolder.relativeDiskon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tvDataProduks.size();
    }

    public void setFilter(List<Model> filterdata) {
        this.tvDataProduks = filterdata;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, tvxdiskon, stok, terjual, deskripsi, type;
//        private ImageView foto_produk;
        private SmartImageView foto_produk;
        RelativeLayout relativeDiskon;


        public ViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.titleproduk);
            hargaProduk = itemView.findViewById(R.id.txthargaawal);
            tvxdiskon = itemView.findViewById(R.id.tvxdiskon);
            type = itemView.findViewById(R.id.typeproduk);
            foto_produk = itemView.findViewById(R.id.imageproduk);
            relativeDiskon = itemView.findViewById(R.id.relativediskon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Model myNewsmodel = tvDataProduks.get(getAdapterPosition());
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("id_produk", String.valueOf(myNewsmodel.getIdProduk()));
                    intent.putExtra("nama_produk", myNewsmodel.getNamaProduk());
                    intent.putExtra("foto_produk", CONSTANTS.SUB_DOMAIN + myNewsmodel.getFoto().get(0).getFotoProduk());
                    intent.putExtra("harga_jual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("stok", String.valueOf(myNewsmodel.getStok()));
                    intent.putExtra("terjual", String.valueOf(myNewsmodel.getHargaJual()));
                    intent.putExtra("keterangan", myNewsmodel.getKeterangan());
                    intent.putExtra("kategori", myNewsmodel.getKategori().getNamaKategori());
                    intent.putExtra("diskon", String.valueOf(myNewsmodel.getDiskon()));
//                    Toast.makeText(context, "id_produk"+myNewsmodel.getIdProduk(), Toast.LENGTH_SHORT).show();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
        }


    }
}
