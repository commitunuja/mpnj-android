package com.sholeh.marketplacenj.adapter.wishlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.mfragment.FragmentHome;
import com.sholeh.marketplacenj.mfragment.favorit.FragmentFavorite;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.wishlist.modelWishlist;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterWishlist extends BaseAdapter {
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private List<modelWishlist> tvDataWishlist;
    private Context context;

    public AdapterWishlist(Context context, List<modelWishlist> tvDataWishlist) {
        this.context = context;
        this.tvDataWishlist = tvDataWishlist;
    }

    @Override
    public int getCount() {
        return tvDataWishlist.size();
    }

    @Override
    public Object getItem(int pos) {
        return tvDataWishlist.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_wishlistgrid, viewGroup, false);
        }

        int hargaProduk;
        double diskon;
        ImageView imageView, imgdeleteWishlist;

        TextView txtNamaProduk, txthargaNormal, txthargaDiskon;
        txtNamaProduk = view.findViewById(R.id.txtProdukW);
        txthargaNormal = view.findViewById(R.id.txthargaw);
        txthargaDiskon = view.findViewById(R.id.txtdiskonw);
        imageView = view.findViewById(R.id.img_imagew);
        imgdeleteWishlist = view.findViewById(R.id.imgDeleteWishlist);

        final modelWishlist model = tvDataWishlist.get(position);
        txtNamaProduk.setText(String.valueOf(model.getNamaProduk()));




//        txtNamaProduk.setText(model.getNamaProduk());

        Glide.with(view.getContext())
                .load(CONSTANTS.SUB_DOMAIN + model.getFotoProduk())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(imageView);

        hargaProduk = model.getHargaJual();
        diskon= Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getHargaDiskon()))));



        st = new StringTokenizer(formatRupiah.format(hargaProduk), ",");
        String hargaNormal = st.nextToken().trim();
        txthargaNormal.setText(hargaNormal);

        if (diskon == 0){
            txthargaDiskon.setVisibility(View.GONE);
//            txthargaDiskon.setText(" ");

        }else{
            double h = diskon / 100 * hargaProduk;
            double p = hargaProduk - h;
            st = new StringTokenizer(formatRupiah.format(p), ",");
            String harganya = st.nextToken().trim();
            txthargaDiskon.setPaintFlags(txthargaNormal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            txthargaDiskon.setTextColor(context.getResources().getColor(R.color.redTransparent));
            txthargaDiskon.setTypeface(txthargaNormal.getTypeface(), Typeface.NORMAL);
            txthargaDiskon.setVisibility(View.VISIBLE);
            txthargaDiskon.setText(hargaNormal);
            txthargaNormal.setText(harganya);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new FragmentHome();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fa, myFragment).addToBackStack(null).commit();

//                Toast.makeText(context, model.getNamaProduk(), Toast.LENGTH_SHORT).show();
                final modelWishlist model = tvDataWishlist.get(position);
                Context context = view.getContext();
//                Toast.makeText(context, "Intent belum selesai ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id_produk", String.valueOf(model.getIdProduk()));
                intent.putExtra("nama_produk", model.getNamaProduk());
                intent.putExtra("harga_jual", String.valueOf(model.getHargaJual()));
                intent.putExtra("stok", String.valueOf(model.getStok()));
                intent.putExtra("terjual", String.valueOf(model.getHargaJual()));
                intent.putExtra("keterangan", model.getKeterangan());
                intent.putExtra("kategori", model.getKategori().getNamaKategori());
                intent.putExtra("diskon", String.valueOf(model.getHargaDiskon()));
                intent.putExtra("foto_produk", CONSTANTS.SUB_DOMAIN + model.getFotoProduk());
//                    Toast.makeText(context, "id_produk"+myNewsmodel.getIdProduk(), Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });

        imgdeleteWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                String id_produk = String.valueOf(model.getIdProduk());
                String id_wishlist = String.valueOf(model.getIdWishlist());
                APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                Call<ResHapusKeranjang> call = service.hapusProdukWishlist(id_wishlist);
                call.enqueue(new Callback<ResHapusKeranjang>() {
                    @Override
                    public void onResponse(Call<ResHapusKeranjang> call, Response<ResHapusKeranjang> response) {
                        Log.d("deleteWishlist", "onResponse: "+response);
                        if (response.body() != null && response.isSuccessful()) {
////                            if (response.body().getPesan().equalsIgnoreCase("sukses")) {
                                AppUtilits.displayMessage(context, "Sukses hapus produk dari wishlist");
//

//                            ((FragmentFavorite) context).getWishlish();

////                            } else {
////                                AppUtilits.displayMessage(context, "Gagal hapus produk dari keranjang");
////                            }
                        } else {
                            Toast.makeText(context, "Terdapat Kesalahan", Toast.LENGTH_SHORT).show();
//                            AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                        }

                    }

                    @Override
                    public void onFailure(Call<ResHapusKeranjang> call, Throwable t) {
                        Log.d("deleteWishlist", "onerr: "+t);
//                        AppUtilits.displayMessage(context, context.getString(R.string.failed_request));

                    }
                });
//        }

            }
        });

        return view;
    }


}
