package com.sholeh.marketplacenj.adapter.keranjang;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.respon.DataKeranjang;
import com.sholeh.marketplacenj.respon.ItemKeranjang;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.respon.ResKeranjang;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.respon.ResUbahJumlahProduk;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpandListScanAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    int hargaProduk, totalHarga, stokProduk, jumlah;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st;



    public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
        this.context = context;
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return this.listHeaderFilter.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChild.get(this.listHeaderFilter.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeaderFilter.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listChild.get(this.listHeaderFilter.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // set on click change
        HeaderModel model = (HeaderModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.desain_parent, null);
        }
        TextView nama_kk = convertView.findViewById(R.id.txNamaToko);
        TextView no_pelanggan = convertView.findViewById(R.id.tvxIdToko);



        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());
        ImageView img = convertView.findViewById(R.id.imgpanah);

        if (isExpanded) {
            //Toast.makeText(context, ""+isExpanded, Toast.LENGTH_SHORT).show();
            img.setImageResource(R.drawable.ic_keyboard_arrow_up_grey_24dp);
        } else {
            img.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_24dp);
            // Toast.makeText(context, ""+isExpanded, Toast.LENGTH_SHORT).show();
        }
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildModel model = (ChildModel) getChild(groupPosition, childPosition);
        String id_keranjang = model.getId_keranjang();
        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();

        LayoutInflater inflater2 = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater2.inflate(R.layout.desain_child, null);

        LinearLayout viewline, increment, decrement;
        final TextView addjumlah, stok, harga, hargaDiskon;
        final ImageView delete_item;

        TextView nama = convertView.findViewById(R.id.txtnamaPRODUK);
        TextView tvxIdKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        harga = convertView.findViewById(R.id.txtharga);
        hargaDiskon = convertView.findViewById(R.id.txthargaDiskon);
        ImageView gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        stok = convertView.findViewById(R.id.txtstock);
        addjumlah = convertView.findViewById(R.id.txt_addjumlah);
        increment = convertView.findViewById(R.id.increment);
        decrement = convertView.findViewById(R.id.decrement);
        delete_item = convertView.findViewById(R.id.cart_delete);

        hargaProduk = model.getHarga();
        stokProduk = model.getStok();
        jumlah = model.getJumlah();
        vdiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getDiskon()))));
        stok.setText(String.valueOf(stokProduk));
        nama.setText(model.getNama_produk());
        tvxIdKeranjang.setText(String.valueOf(id_keranjang));

        addjumlah.setText(String.valueOf(jumlah));
        int hitungJumHarga = jumlah * hargaProduk;
        st = new StringTokenizer(formatRupiah.format(hitungJumHarga), ",");
        String hargajum = st.nextToken().trim();
        harga.setText(hargajum);

        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + model.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(gambar);


        if (model.getDiskon() == 0) {


        } else {
            double h = vdiskon / 100 * hargaProduk;
            double p = hargaProduk - h;
            double hitung = jumlah * p;
            st = new StringTokenizer(formatRupiah.format(hitung), ",");
            String harganya = st.nextToken().trim();
            harga.setVisibility(View.GONE);
            hargaDiskon.setVisibility(View.VISIBLE);
            hargaDiskon.setText(harganya);

        }
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(addjumlah.getText().toString());
                int stokproduk = Integer.valueOf(stok.getText().toString());
                String id_keranjang = model.getId_keranjang();
                final double potongandiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getDiskon()))));

                if (count < stokproduk) {
                    count++;
                    addjumlah.setText("" + count);
                    APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                    Call<ResUbahJumlahProduk> call = service.updateJumlah(id_keranjang, addjumlah.getText().toString());
                    call.enqueue(new Callback<ResUbahJumlahProduk>() {
                        @Override
                        public void onResponse(Call<ResUbahJumlahProduk> call, Response<ResUbahJumlahProduk> response) {

                            if (response.body() != null && response.isSuccessful()) {
                                double tot = response.body().getJumlah();
                                st = new StringTokenizer(formatRupiah.format(tot), ",");
                                String sett = st.nextToken().trim();
                                harga.setText(sett);

                                double h = potongandiskon / 100 * tot;
                                double p = tot - h;
                                st = new StringTokenizer(formatRupiah.format(p), ",");
                                String harganya = st.nextToken().trim();

                                hargaDiskon.setText(harganya);
                                getTotal();

                            } else {
                                Toast.makeText(context, "" + response.body(), Toast.LENGTH_SHORT).show();
//                                AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                            }
                        }

                        @Override
                        public void onFailure(Call<ResUbahJumlahProduk> call, Throwable t) {
//                            Log.e(TAG, " edit fail "+ t.toString());
//                            AppUtilits.displayMessage(mContext,  mContext.getString(R.string.fail_toeditcart));
                        }
                    });

//        }
                } else if (count == stokproduk) {
                    Toast.makeText(context, "Stok Barang Hanya Tersedia " + stokproduk, Toast.LENGTH_SHORT).show();
                }
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.valueOf(addjumlah.getText().toString());
                String id_keranjang = model.getId_keranjang();
                final double potongandiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getDiskon()))));

                if (count > 1) {
                    count--;
                    addjumlah.setText("" + count);

                    APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                    Call<ResUbahJumlahProduk> call = service.updateJumlah(id_keranjang, addjumlah.getText().toString());
                    call.enqueue(new Callback<ResUbahJumlahProduk>() {
                        @Override
                        public void onResponse(Call<ResUbahJumlahProduk> call, Response<ResUbahJumlahProduk> response) {

                            if (response.body() != null && response.isSuccessful()) {

                                double totjumlah = response.body().getJumlah();
                                st = new StringTokenizer(formatRupiah.format(totjumlah), ",");
                                String set = st.nextToken().trim();
                                double h = potongandiskon / 100 * totjumlah;
                                double p = totjumlah - h;
                                st = new StringTokenizer(formatRupiah.format(p), ",");
                                String harganya =  st.nextToken().trim();
                                hargaDiskon.setText(harganya);
                                harga.setText(set);

                                getTotal();
//
                            } else {
                                Toast.makeText(context, "" + response.body(), Toast.LENGTH_SHORT).show();
//                                AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                            }
                        }


                        @Override
                        public void onFailure(Call<ResUbahJumlahProduk> call, Throwable t) {
//                            Log.e(TAG, " edit fail "+ t.toString());
//                            AppUtilits.displayMessage(mContext,  mContext.getString(R.string.fail_toeditcart));
                        }
                    });
                }
            }
        });

        delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_keranjang = model.getId_keranjang();

                //        if (!NetworkUtility.isNetworkConnected(mContext)){
//            AppUtilits.displayMessage(mContext,  mContext.getString(R.string.network_not_connected));
//
//        }else {
//            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
                APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);

                Call<ResHapusKeranjang> call = service.hapusProdukKeranjang(id_keranjang);
                call.enqueue(new Callback<ResHapusKeranjang>() {
                    @Override
                    public void onResponse(Call<ResHapusKeranjang> call, Response<ResHapusKeranjang> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (response.body().getPesan().equalsIgnoreCase("sukses")) {
//                                Toast.makeText(context, "berhasil", Toast.LENGTH_SHORT).show();
                                AppUtilits.displayMessage(context, "Sukses hapus produk dari keranjang");

                                ((KeranjangDetailActivity) context).getDetailKeranjang();

                                // update cart count
                                //    SharePreferenceUtils.getInstance().saveInt( Constant.CART_ITEM_COUNT,   SharePreferenceUtils.getInstance().getInteger(Constant.CART_ITEM_COUNT) -1);
                                //    AppUtilits.UpdateCartCount(mContext, CartDetails.mainmenu);

                            } else {
                                AppUtilits.displayMessage(context, "Gagal hapus produk dari keranjang");
                            }
                        } else {
//                            AppUtilits.displayMessage(mContext, mContext.getString(R.string.network_error));
                        }

                    }

                    @Override
                    public void onFailure(Call<ResHapusKeranjang> call, Throwable t) {
                        AppUtilits.displayMessage(context, context.getString(R.string.failed_request));

                    }
                });
//        }
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void getTotal() {
//        if (!NetworkUtility.isNetworkConnected(KeranjangDetailActivity.this)){
//            AppUtilits.displayMessage(KeranjangDetailActivity.this,  getString(R.string.network_not_connected));
//
//        }else {
        //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang(id_konsumen);
        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, retrofit2.Response<ResDetailKeranjang> response) {
                totalHarga = Integer.parseInt(String.valueOf(response.body().getTotalHarganya()));
                Intent intent = new Intent("custom-message");
                intent.putExtra("total", String.valueOf(totalHarga));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                //    tvx_total.setText("Rp "+totalHarga);
            }

            @Override
            public void onFailure(Call<ResDetailKeranjang> call, Throwable t) {
//                Toast.makeText(KeranjangDetailActivity.this, "e"+t, Toast.LENGTH_SHORT).show();
                //  Log.e(TAG, "  fail- add to cart item "+ t.toString());
//                AppUtilits.displayMessage(KeranjangDetailActivity.this, getString(R.string.fail_toGetcart));

                Log.d("cekkk", String.valueOf(t));
                Toast.makeText(context, "cekk" + t, Toast.LENGTH_SHORT).show();
            }
        });
//        }
    }

}