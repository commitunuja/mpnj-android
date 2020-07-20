package com.sholeh.marketplacenj.adapter.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.custom.Product;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.Produk;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Model> datapencarian;
//    List<Model> datapencarian2;
    private Model modelpencarian;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer stringTokenizer;


    public SearchAdapter(Context context, List<Model> datapencarian) {
        this.context = context;
        this.datapencarian = datapencarian;


    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_search, viewGroup, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int i) {
        modelpencarian = datapencarian.get(i);
        viewHolder.namaProduk.setText(modelpencarian.getNamaProduk()); // MODEL
        viewHolder.type.setText(modelpencarian.getKategori().getNamaKategori());

        int hargaAwal = modelpencarian.getHargaJual();
        double diskon = modelpencarian.getDiskon();
        double h = diskon / 100 * hargaAwal;
        double p = hargaAwal - h;

        stringTokenizer = new StringTokenizer(formatRupiah.format(p), ",");
        String hargajum = stringTokenizer.nextToken().trim();
        viewHolder.hargaProduk.setText(hargajum);
        
        Picasso.with(context)
                .load(CONSTANTS.SUB_DOMAIN + modelpencarian.getFoto().get(0).getFotoProduk())
                .resize(300, 300)
                .into(viewHolder.foto_produk);

        if(diskon == 0){
           viewHolder.diskon.setVisibility(View.GONE);
        }else{
            viewHolder.diskon.setText(modelpencarian.getDiskon()+"%");
            viewHolder.diskon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return datapencarian.size();
    }

    public void setFilter(List<Model> filterdata) {
        this.datapencarian = filterdata;
        notifyDataSetChanged();
    }
//    public void setFilter2(List<Model> filterdata2) {
//        this.datapencarian2 = filterdata2;
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namaProduk, hargaProduk, stok, terjual, deskripsi, type, diskon;
        private ImageView foto_produk;


        public ViewHolder(View itemView) {
            super(itemView);

            namaProduk = itemView.findViewById(R.id.titleproduk);
            hargaProduk = itemView.findViewById(R.id.txthargaawal);
            type = itemView.findViewById(R.id.typeproduk);
            diskon = itemView.findViewById(R.id.tvx_diskon);
            foto_produk = itemView.findViewById(R.id.imageproduk);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Model myNewsmodel = datapencarian.get(getAdapterPosition());
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
