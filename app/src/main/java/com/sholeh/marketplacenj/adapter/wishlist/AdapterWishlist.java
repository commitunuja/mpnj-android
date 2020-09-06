package com.sholeh.marketplacenj.adapter.wishlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
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

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterWishlist extends BaseAdapter {
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private List<modelWishlist> tvDataWishlist;
    private Context context;
    private KProgressHUD progressHud;
    private Fragment fragment;


    public AdapterWishlist(Context context, List<modelWishlist> tvDataWishlist, Fragment fragment) {
        this.context = context;
        this.tvDataWishlist = tvDataWishlist;
        this.fragment = fragment;
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
        progressHud = KProgressHUD.create(context);
        RelativeLayout relativeDiskon;

        TextView txtNamaProduk, txthargaNormal, txthargaDiskon, tvxJumterjual, tvterjual, tvx_kabPenjual, tvxJumlahDiskon;
        txtNamaProduk = view.findViewById(R.id.txtProdukW);
        txthargaNormal = view.findViewById(R.id.txthargaw);
        txthargaDiskon = view.findViewById(R.id.txtdiskonw);
        tvxJumlahDiskon = view.findViewById(R.id.tvx_jumlahDiskon);
        tvxJumterjual= view.findViewById(R.id.tvx_jumterjual);
        tvterjual = view.findViewById(R.id.tvTerjual);
        tvx_kabPenjual = view.findViewById(R.id.tvxkabPenjual);
        imageView = view.findViewById(R.id.img_imagew);
        imgdeleteWishlist = view.findViewById(R.id.imgDeleteWishlist);
        relativeDiskon = view.findViewById(R.id.rdiskon);

        final modelWishlist model = tvDataWishlist.get(position);
        txtNamaProduk.setText(String.valueOf(model.getNamaProduk()));
        tvx_kabPenjual.setText(String.valueOf(model.getKabPenjual()));


        int radius = 17;
        int margin = 0;
        Glide.with(view.getContext())
                .load(CONSTANTS.SUB_DOMAIN + model.getFotoProduk())
                .apply(new RequestOptions().override(250, 250))
                .placeholder(R.drawable.no_image)
                .error(R.drawable.img1)
                .centerCrop()
                .transform(new RoundedCornersTransformation(radius, margin))
                .into(imageView);

        hargaProduk = model.getHargaJual();
        diskon= Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getHargaDiskon()))));



        st = new StringTokenizer(formatRupiah.format(hargaProduk), ",");
        String hargaNormal = st.nextToken().trim();
        txthargaNormal.setText(hargaNormal);

        if (diskon == 0){
            txthargaDiskon.setVisibility(View.GONE);
            txthargaDiskon.setText(" ");
            relativeDiskon.setVisibility(View.GONE);

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
            txthargaDiskon.setTextSize(10);
            txthargaNormal.setText(harganya);
            relativeDiskon.setVisibility(View.VISIBLE);
            int jumDiskon = (int) diskon;
            tvxJumlahDiskon.setText(jumDiskon+"%");
        }

        int terjual = model.getTerjual();
        if (terjual == 0){
            tvxJumterjual.setVisibility(View.GONE);
            tvterjual.setText("Belum Terjual");
        }else {
            tvxJumterjual.setText(String.valueOf(terjual));
            tvterjual.setText("Terjual");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new FragmentHome();
                final modelWishlist model = tvDataWishlist.get(position);
                Context context = view.getContext();
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
                String id_produk = String.valueOf(model.getIdProduk());
                String id_wishlist = String.valueOf(model.getIdWishlist());
                ProgresDialog();
                APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                Call<ResHapusKeranjang> call = service.hapusProdukWishlist(id_wishlist);
                call.enqueue(new Callback<ResHapusKeranjang>() {
                    @Override
                    public void onResponse(Call<ResHapusKeranjang> call, Response<ResHapusKeranjang> response) {
                        Log.d("deleteWishlist", "onResponse: "+response);
                        if (response.body() != null && response.isSuccessful()) {
                            progressHud.dismiss();
                            ((FragmentFavorite) fragment).getWishlish();
                        } else {
                            progressHud.dismiss();
                            AppUtilits.displayMessage(context, String.valueOf((R.string.network_error)));
//                            Toast.makeText(context, "Terdapat Kesalahan Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResHapusKeranjang> call, Throwable t) {
                        Log.d("deleteWishlist", "onerr: "+t);
                        progressHud.dismiss();
                        Toast.makeText(context, "Silakan coba lagi. Jaringan tidak merespon", Toast.LENGTH_SHORT).show();
//                        AppUtilits.displayMessage(context, context.getString(R.string.failed_request));

                    }
                });
//        }

            }
        });

        return view;
    }

    private void ProgresDialog() {
        progressHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressHud.show();
    }

}
