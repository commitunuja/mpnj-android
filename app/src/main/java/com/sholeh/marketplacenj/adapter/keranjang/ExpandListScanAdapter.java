package com.sholeh.marketplacenj.adapter.keranjang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.model.keranjang.FirstModel;
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
import java.util.Map;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpandListScanAdapter extends BaseExpandableListAdapter {
    int totot = 0;
    int jumlahProduk = 0;
    private Context context;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
//    List<ChildModel> child;
    int hargaProduk, totalHarga, stokProduk;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private static final String TAG = "MyExpandAdapter";

    public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
        this.context = context;
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
        setNewItems(listHeader, listChild);
//        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return this.listHeaderFilter.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChild.get(listHeaderFilter.get(groupPosition)).size();

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
        return true;
//        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        setNewItems(listHeaderFilter, listChild);
//        notifyDataSetChanged();
        // set on click change
        final HeaderModel model = (HeaderModel) getGroup(groupPosition);
        final HeaderModel headerModel = listHeaderFilter.get(groupPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_parent, null);
        }


        final TextView nama_kk, no_pelanggan;
        final CheckBox cbparent;

        nama_kk = convertView.findViewById(R.id.txNamaToko);
        no_pelanggan = convertView.findViewById(R.id.tvxIdToko);
        cbparent = convertView.findViewById(R.id.cb_select_parent);

        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());

        cbparent.setChecked(headerModel.isChecked());
        final boolean nowBeanChecked = headerModel.isChecked();
        cbparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);

                int totalCountParent = 0;
                double totalPrice = 0;
                for (int i = 0; i < listHeaderFilter.size(); i++) {
                    List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));
                    for (int j = 0; j < childMapList.size(); j++) {
                        ChildModel childModel = childMapList.get(j);
                        int hargaJual = childModel.getHarga();
                        double diskon = childModel.getDiskon();
                        int jumlah = childModel.getJumlah();
                        if (childModel.isChecked()) {
                            totalCountParent++;
                            double h = diskon / 100 * hargaJual;
                            double s = hargaJual - h;
                            double p = jumlah * s;
                            totalPrice += p;
                        }
                    }
                }

                if (headerModel.isChecked()) {
                    totot += (int) Double.parseDouble(String.valueOf(totalPrice));
                } else {
                    totot -= (int) Double.parseDouble(String.valueOf(totalPrice));
                }
                Intent intent = new Intent("custom-message");
                intent.putExtra("total", String.valueOf(totalPrice));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//                notifyDataSetChanged();

            }
        });

        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d("sholeh", listHeaderFilter.get(groupPosition).getId_toko());



        final ChildModel model = listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);
        final ChildModel childModel = model;
        String id_keranjang = model.getId_keranjang();
        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();
//        getJumlahProduk();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_child, null);
        }

        LinearLayout viewline, increment, decrement;
        final TextView tvx_nama, tvx_idKeranjang, tvx_addjumlah, tvx_stok, tvx_harga, tvx_hargaDiskon;
        final ImageView delete_item, img_gambar;
        final CheckBox cbchild;


        tvx_nama = convertView.findViewById(R.id.txtnamaPRODUK);
        tvx_idKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        tvx_harga = convertView.findViewById(R.id.txtharga);
        tvx_hargaDiskon = convertView.findViewById(R.id.txthargaDiskon);
        img_gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        tvx_stok = convertView.findViewById(R.id.txtstock);
        tvx_addjumlah = convertView.findViewById(R.id.txt_addjumlah);
        increment = convertView.findViewById(R.id.increment);
        decrement = convertView.findViewById(R.id.decrement);
        delete_item = convertView.findViewById(R.id.cart_delete);
        cbchild = convertView.findViewById(R.id.cb_select_child);





//        if (addjumlah == 0){ // agar tidak ke reload kembali ke data sebelumnya
//
//        }else{
//             tvx_addjumlah.setText(String.valueOf(addjumlah));
//        }

        hargaProduk = model.getHarga();
        stokProduk = model.getStok();
        jumlahProduk = model.getJumlah();

        vdiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getDiskon()))));
        tvx_stok.setText(String.valueOf(stokProduk));
        tvx_nama.setText(model.getNama_produk());
        tvx_idKeranjang.setText(String.valueOf(id_keranjang));
        tvx_addjumlah.setText(String.valueOf(jumlahProduk));
//        setNewItems(listHeaderFilter, listChild);


//        Toast.makeText(context, "jumlah produk "+jumlahProduk, Toast.LENGTH_SHORT).show();
        int hitungJumHarga = jumlahProduk * hargaProduk;
        st = new StringTokenizer(formatRupiah.format(hitungJumHarga), ",");
        String hargajum = st.nextToken().trim();
        tvx_harga.setText(hargajum);


        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + model.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(img_gambar);


        if (model.getDiskon() == 0) {
//            Toast.makeText(context, "no diskon", Toast.LENGTH_SHORT).show();
            tvx_hargaDiskon.setVisibility(View.GONE);

        } else {
            double h = vdiskon / 100 * hargaProduk;
            double p = hargaProduk - h;
            double hitung = jumlahProduk * p;
            st = new StringTokenizer(formatRupiah.format(hitung), ",");
            String harganya = st.nextToken().trim();
//            harga.setVisibility(View.GONE);
            tvx_hargaDiskon.setPaintFlags(tvx_harga.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvx_hargaDiskon.setTextColor(context.getResources().getColor(R.color.redTransparent));
            tvx_hargaDiskon.setTypeface(tvx_harga.getTypeface(), Typeface.NORMAL);
            tvx_hargaDiskon.setVisibility(View.VISIBLE);
            tvx_hargaDiskon.setText(hargajum);
            tvx_harga.setText(harganya);

        }

        cbchild.setChecked(childModel.isChecked());
        cbchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean nowBeanChecked = childModel.isChecked();
                childModel.setIsChecked(!nowBeanChecked);

                boolean parentIsChecked = dealOneParentAllChildIsChecked(groupPosition);
                HeaderModel headerModel = listHeaderFilter.get(groupPosition);


                String harganya = tvx_harga.getText().toString();
                String[] nomor = harganya.split("Rp");
                String[] nomor2 = nomor[1].split("\\.");
                String har = "";
                for (int i = 0; i < nomor2.length; i++) {
                    har = har + nomor2[i];
                }
                if (childModel.isChecked()) {
                    totot += Integer.parseInt(har);
                } else {
                    totot -= Integer.parseInt(har);

                }
                Intent intent = new Intent("custom-message");
                intent.putExtra("total", String.valueOf(totot));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                headerModel.setIsChecked(parentIsChecked);
                notifyDataSetChanged();


//                dealPrice();
            }
        });
//
        cbchild.setChecked(childModel.isChecked());
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahProduk = Integer.valueOf(tvx_addjumlah.getText().toString());
                int stokproduk = Integer.valueOf(tvx_stok.getText().toString());
                String id_keranjang = model.getId_keranjang();
                final double potongandiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getDiskon()))));
//                addjumlah.setText(String.valueOf(count));
                if (jumlahProduk < stokproduk) {
                    jumlahProduk++;
                    tvx_addjumlah.setText("" + jumlahProduk);

                    APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                    Call<ResUbahJumlahProduk> call = service.updateJumlah(id_keranjang, String.valueOf(jumlahProduk));
                    call.enqueue(new Callback<ResUbahJumlahProduk>() {
                        @Override
                        public void onResponse(Call<ResUbahJumlahProduk> call, Response<ResUbahJumlahProduk> response) {
                            // addjumlah.setText("" + count); agar ketika di scroll jumlah tidak kembali ke sebelumnya
                            if (response.body() != null && response.isSuccessful()) {
                                int tot = response.body().getJumlah(); // harga total
//                                if (model.getDiskon() == 0) {
                                st = new StringTokenizer(formatRupiah.format(tot), ",");
                                String sett = st.nextToken().trim();
                                tvx_harga.setText(sett);


//                                    hargaDiskon.setText(hargajum);
//                                    String hargaaa = harga.getText().toString();
//                                    String[] nomor = hargaaa.split("Rp");
//                                    String[] nomor2 = nomor[1].split("\\.");
//                                    String har = "";
//                                    for (int i = 0; i < nomor2.length; i++){
//                                        har = har+ nomor2[i];
//                                    }
//                                    if (childModel.isChecked()) {

//                                        int totalCountParent = 0;
//                                        double totalPrice = 0;
//                                        double p = 0;
//                                        for (int i = 0; i < listHeaderFilter.size(); i++) {
//                                            List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));
//                                            for (int j = 0; j < childMapList.size(); j++) {
//                                                ChildModel childModel = childMapList.get(j);
//                                                int hargaJual = childModel.getHarga();
//                                                double diskon = childModel.getDiskon();
//                                                int jumlah = childModel.getJumlah();
//                                                if (childModel.isChecked()) {
//                                                    totalCountParent++;
//                                                    double h = diskon / 100 * hargaJual;
//                                                    double s = hargaJual - h;
//                                                    p = jumlah * s;
//                                                    totalPrice += p;
//
//                                                }
//                                            }
//                                        }
//                                        totalPrice += model.getHarga();


//                                        totot += model.getHarga();
//                                        Toast.makeText(context, "to "+totot, Toast.LENGTH_LONG).show();

//                                        Intent intent = new Intent("custom-message");
//                                        intent.putExtra("total", String.valueOf(totot));
//                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

//                                    }


//                                } else {
                                int hargaProduk = model.getHarga();
                                int jumlahProduk = Integer.parseInt(String.valueOf(tvx_addjumlah.getText()));
                                int hitung = jumlahProduk * hargaProduk;
                                double h = potongandiskon / 100 * hargaProduk;
                                double v = hargaProduk - h;
                                double p = v * jumlahProduk;
                                st = new StringTokenizer(formatRupiah.format(p), ",");
                                st2 = new StringTokenizer(formatRupiah.format(hitung), ",");
                                String harganya = st.nextToken().trim();
                                String totalnya = st2.nextToken().trim();
                                tvx_harga.setText(harganya); // harga setelah diskon
                                tvx_hargaDiskon.setText(totalnya);


                                int totalCountParent = 0;
                                double totalPrice = 0;
                                double cekHarga = 0;
                                double gettot = 0;
                                for (int i = 0; i < listHeaderFilter.size(); i++) {
                                    List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));
                                    for (int j = 0; j < childMapList.size(); j++) {
                                        ChildModel childModel = childMapList.get(j);
//                                        int hargaJual = Integer.parseInt(harga.getText().toString());
                                        double diskon = childModel.getDiskon();
//                                        int jumlah = childModel.getJumlah();
                                        if (childModel.isChecked()) {
                                            totalCountParent++;
//                                            double hh = diskon / 100 * hargaJual;
//                                            double s = hargaJual - hh;
//                                            double s = p;

//                                            cekHarga += s;
//
                                            gettot = jumlahProduk * cekHarga; // jumlah dikali harga setelah diskon
                                            Toast.makeText(context, "j"+p, Toast.LENGTH_SHORT).show();
//                                            totalPrice =pp;
//                                            pp += hargaJual;
//                                            gettot = pp;

                                        }
                                    }
                                }


//                                    harga.setVisibility(View.GONE);
//                                    String hargaaa = harga.getText().toString();
//                                    String[] nomor = hargaaa.split("Rp");
//                                    String[] nomor2 = nomor[1].split("\\.");
//                                    String har = "";
//                                    for (int i = 0; i < nomor2.length; i++){
//                                        har = har+ nomor2[i];
//                                    }
//                                    if (childModel.isChecked()) {
//
//                                        totot += p;
                                Toast.makeText(context, "harga " + v + " h" + p + "tot "+cekHarga, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent("custom-message");
                                intent.putExtra("total", String.valueOf(cekHarga));
                                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//
//                                    }
//                                }
//                                getTotal();

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
                } else if (jumlahProduk == stokproduk) {
                    Toast.makeText(context, "Stok Barang Hanya Tersedia " + stokproduk, Toast.LENGTH_SHORT).show();
                }
            }
        });
//
        cbchild.setChecked(childModel.isChecked());
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlahProduk = Integer.valueOf(tvx_addjumlah.getText().toString());
                String id_keranjang = model.getId_keranjang();
                final double potongandiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(model.getDiskon()))));
//                addjumlah.setText(String.valueOf(count));
                if (jumlahProduk > 1) {
                    jumlahProduk--;
                    tvx_addjumlah.setText("" + jumlahProduk);

                    APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                    Call<ResUbahJumlahProduk> call = service.updateJumlah(id_keranjang, String.valueOf(jumlahProduk));
                    call.enqueue(new Callback<ResUbahJumlahProduk>() {
                        @Override
                        public void onResponse(Call<ResUbahJumlahProduk> call, Response<ResUbahJumlahProduk> response) {
                            if (response.body() != null && response.isSuccessful()) {
                                int tot = response.body().getJumlah();
                                if (model.getDiskon() == 0) {
                                    st = new StringTokenizer(formatRupiah.format(tot), ",");
                                    String sett = st.nextToken().trim();
                                    tvx_harga.setText(sett);
//                                    hargaDiskon.setText(hargajum);
                                    String hargaaa = tvx_harga.getText().toString();
                                    String[] nomor = hargaaa.split("Rp");
                                    String[] nomor2 = nomor[1].split("\\.");
                                    String har = "";
                                    for (int i = 0; i < nomor2.length; i++) {
                                        har = har + nomor2[i];
                                    }
                                    if (childModel.isChecked()) {
                                        totot -= model.getHarga();
//                                        Intent intent = new Intent("custom-message");
//                                        intent.putExtra("total", String.valueOf(totot));
//                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                                    }

                                } else {
                                    int hargaProduk = model.getHarga();
                                    int jumlahProduk = Integer.parseInt(String.valueOf(tvx_addjumlah.getText()));
                                    int hitung = jumlahProduk * hargaProduk;
                                    double h = potongandiskon / 100 * hargaProduk;
                                    double v = hargaProduk - h;
                                    double p = v * jumlahProduk;
                                    st = new StringTokenizer(formatRupiah.format(p), ",");
                                    st2 = new StringTokenizer(formatRupiah.format(hitung), ",");
                                    String harganya = st.nextToken().trim();
                                    String totalnya = st2.nextToken().trim();
                                    tvx_harga.setText(harganya);
                                    tvx_hargaDiskon.setText(String.valueOf(totalnya));

//                                    harga.setVisibility(View.GONE);
                                    String hargaaa = tvx_harga.getText().toString();
                                    String[] nomor = hargaaa.split("Rp");
                                    String[] nomor2 = nomor[1].split("\\.");
                                    String har = "";
                                    for (int i = 0; i < nomor2.length; i++) {
                                        har = har + nomor2[i];
                                    }
                                    if (childModel.isChecked()) {
                                        totot -= v;
                                        Intent intent = new Intent("custom-message");
                                        intent.putExtra("total", String.valueOf(totot));
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                                    }
                                }
//                                getTotal();
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

    private void setupOneParentAllChildChecked(boolean isChecked, int groupPosition) { //b
        HeaderModel headerModel = listHeaderFilter.get(groupPosition);
        headerModel.setIsChecked(isChecked);
        for (int i = 0; i < listChild.get(listHeaderFilter.get(groupPosition)).size(); i++) {
            ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(i);
            childModel.setIsChecked(isChecked);
        }
        notifyDataSetChanged();

    }

    public void setupAllChecked(boolean isChecked) {
        for (int i = 0; i < listHeaderFilter.size(); i++) {
            HeaderModel headerModel = listHeaderFilter.get(i);
            headerModel.setIsChecked(isChecked);
            List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));
            for (int j = 0; j < childMapList.size(); j++) {
                ChildModel childModel = childMapList.get(j);
                childModel.setIsChecked(isChecked);
            }
        }
        notifyDataSetChanged();
    }


    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
//        List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));

        for (int j = 0; j < listChild.get(listHeaderFilter.get(groupPosition)).size(); j++) {
            ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(j);
            if (!childModel.isChecked()) { // jika sudah tidak di pilih

                return false;
            }
        }
        return true;
    }

    public void setNewItems(List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
        notifyDataSetChanged();
    }



}