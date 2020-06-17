package com.sholeh.marketplacenj.adapter.checkout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
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
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AddAlamat;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.activities.keranjang.KeranjangDetailActivity;
import com.sholeh.marketplacenj.activities.kurir.OpsiPengirimanActivity;
import com.sholeh.marketplacenj.model.Model;
import com.sholeh.marketplacenj.model.checkout.ChildCheckout;
import com.sholeh.marketplacenj.model.checkout.HeaderCheckout;
import com.sholeh.marketplacenj.model.keranjang.ChildModel;
import com.sholeh.marketplacenj.model.keranjang.HeaderModel;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.respon.ResHapusKeranjang;
import com.sholeh.marketplacenj.respon.ResUbahJumlahProduk;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
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

public class ExpandAdapterCheckout extends BaseExpandableListAdapter {
    int jumlahProduk = 0;
    double totalHarga = 0;
    private Context context;
    private List<HeaderCheckout> listHeaderFilter;
    private HashMap<HeaderCheckout, List<ChildCheckout>> listChild;
    int hargaProduk, stokProduk;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private static final String TAG = "MyExpandAdapter";
    String CUSTOM_ACTION = "com.example.YOUR_ACTION";
    String id_kabupate, id_kecamatan;
    Integer total_berat;

    CheckoutActivity checkoutActivity;

    public ExpandAdapterCheckout(Context context, List<HeaderCheckout> listHeader, HashMap<HeaderCheckout, List<ChildCheckout>> listChild) {
        this.listHeaderFilter = listHeader;
        this.listChild = listChild;
        this.context = context;
//        Integer my_id = ((CheckoutActivity) context.getApplicationContext()()).getMy_id();
//        checkoutActivity = (CheckoutActivity) activity;
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
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final HeaderCheckout model = (HeaderCheckout) getGroup(groupPosition);
        final HeaderCheckout headerModel = listHeaderFilter.get(groupPosition);
        final List<ChildCheckout> childModel = listChild.get(listHeaderFilter.get(groupPosition));


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_parent_checkout, null);
        }


        final TextView nama_kk, no_pelanggan, idKabPenjual,  tvx_OpsiKurir1;

        nama_kk = convertView.findViewById(R.id.txtNamaToko);
        no_pelanggan = convertView.findViewById(R.id.txtIdToko);
        idKabPenjual = convertView.findViewById(R.id.txtIdKabPenjual);
//        tvx_OpsiKurir1 = convertView.findViewById(R.id.tvx_opsiKurir1);

        nama_kk.setText(model.getNama_toko());
        no_pelanggan.setText(model.getId_toko());
        idKabPenjual.setText(model.getId_kabToko());
//        tvx_OpsiKurir1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HeaderCheckout myNewsheader =listHeaderFilter.get(groupPosition);
//                Context context = v.getContext();
//                ResDetailKeranjang bs = ((CheckoutActivity) context).getbs();
//                String idKabPenjual = bs.getDataKeranjang().get(groupPosition).getIdKabupaten();
//                String idKecPembeli = bs.getPembeli().getIdKecamatan();
//                Intent intent= new Intent(context, OpsiPengirimanActivity.class);
//                intent.putExtra("idkab_toko", String.valueOf(idKabPenjual));
//                intent.putExtra("idkec_pembeli", String.valueOf(idKecPembeli));
//                context.startActivity(intent);
////                Toast.makeText(context, ""+groupPosition+" "+childPosition, Toast.LENGTH_SHORT).show();
//            }
//        });


        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        Log.d("sholeh", listHeaderFilter.get(groupPosition).getId_toko());

        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_child_checkout, null);
        }

        final TextView tvx_nama, tvx_idKeranjang, tvx_jumlahproduk,  tvx_harga, tvx_totharga, tvx_hargaDiskon, tvx_OpsiKurir1;
        final ImageView img_gambar;
            final ChildCheckout childCheckout = listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);

        tvx_nama = convertView.findViewById(R.id.txtnamaPRODUK);
        tvx_idKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        tvx_harga = convertView.findViewById(R.id.tvx_harga);
        tvx_totharga = convertView.findViewById(R.id.tvx_hargajum);
        tvx_hargaDiskon = convertView.findViewById(R.id.tvxHrgaDiskon);
        tvx_jumlahproduk = convertView.findViewById(R.id.tvxJumlahProduk);
        tvx_OpsiKurir1 = convertView.findViewById(R.id.tvx_opsiKurir1);

        img_gambar = convertView.findViewById(R.id.img_gambarkeranjang);


        hargaProduk = childCheckout.getHarga();
        jumlahProduk = childCheckout.getJumlah();



        vdiskon = Double.parseDouble(String.valueOf(Integer.parseInt(String.valueOf(childCheckout.getDiskon()))));
        tvx_nama.setText(childCheckout.getNama_produk());
        tvx_idKeranjang.setText(childCheckout.getId_keranjang());
        tvx_jumlahproduk.setText(String.valueOf(jumlahProduk));

        int hitungJumHarga = jumlahProduk * hargaProduk;
        st = new StringTokenizer(formatRupiah.format(hitungJumHarga), ",");
        String hargajum = st.nextToken().trim();
        tvx_harga.setText(hargajum);
        tvx_totharga.setText(hargajum);



        Glide.with(convertView.getContext())
                .load(CONSTANTS.SUB_DOMAIN + childCheckout.getGambar())
                .apply(new RequestOptions().override(350, 550))
                .placeholder(R.drawable.img)
                .error(R.drawable.img1)
                .into(img_gambar);


        if (childCheckout.getDiskon() == 0) {
            tvx_hargaDiskon.setVisibility(View.GONE);
        } else {
            double h = vdiskon / 100 * hargaProduk;
            double p = hargaProduk - h;
            double hitung = jumlahProduk * p;
            st = new StringTokenizer(formatRupiah.format(hitung), ",");
            String harganya = st.nextToken().trim();
            tvx_hargaDiskon.setPaintFlags(tvx_harga.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvx_hargaDiskon.setTextColor(context.getResources().getColor(R.color.redTransparent));
            tvx_hargaDiskon.setTypeface(tvx_harga.getTypeface(), Typeface.NORMAL);
            tvx_hargaDiskon.setVisibility(View.VISIBLE);
            tvx_hargaDiskon.setText(hargajum);
            tvx_harga.setText(harganya);
            tvx_totharga.setText(harganya);
        }

        tvx_OpsiKurir1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeaderCheckout myNewsheader =listHeaderFilter.get(groupPosition);
                Context context = v.getContext();
                ResDetailKeranjang bs = ((CheckoutActivity) context).getbs();
                String idKabPenjual = bs.getDataKeranjang().get(groupPosition).getIdKabupaten();
                String idKecPembeli = bs.getPembeli().getIdKecamatan();
                Intent intent= new Intent(context, OpsiPengirimanActivity.class);
                intent.putExtra("idkab_toko", String.valueOf(idKabPenjual));
                intent.putExtra("idkec_pembeli", String.valueOf(idKecPembeli));
                context.startActivity(intent);
//                Toast.makeText(context, ""+groupPosition+" "+childPosition, Toast.LENGTH_SHORT).show();
            }
        });

        getTotal();

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void getTotal() {
        jumlahProduk = 0; //totalCount
        totalHarga = 0;;
        ArrayList<String> myArray = new ArrayList<>();
        String  getid = null;

        for (int i = 0; i < listHeaderFilter.size(); i++) {
            List<ChildCheckout> childMapList = listChild.get(listHeaderFilter.get(i));

            for (int j = 0; j < childMapList.size(); j++) {
                ChildCheckout childModel = childMapList.get(j);
                int jumlah = childMapList.get(j).getJumlah();
                double Harga = childMapList.get(j).getHarga();
                double diskonHarga = childModel.getDiskon();
                double h = diskonHarga / 100 * Harga;
                double p = Harga - h;
                totalHarga += p * jumlah;


//                Intent intent = new Intent("custom-message");
//                intent.putExtra("total", String.valueOf(p));
//                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);





            }
        }
        Intent intent = new Intent("custom-message");
        intent.putExtra("total", String.valueOf(totalHarga));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }
}