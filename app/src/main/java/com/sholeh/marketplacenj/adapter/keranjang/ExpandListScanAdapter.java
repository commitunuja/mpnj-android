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
    private Context context;
    private List<HeaderModel> listHeaderFilter;
    private HashMap<HeaderModel, List<ChildModel>> listChild;
    private List<ChildModel> anak;
    int hargaProduk, totalHarga, stokProduk, jumlah;
    Preferences preferences;
    String id_konsumen;
    Double vdiskon;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    StringTokenizer st, st2;
    private static final String TAG = "MyExpandAdapter";

    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;
//    OnGoodsCheckedChangeListener onGoodsCheckedChangeListener;
//    OnCheckHasGoodsListener onCheckHasGoodsListener;

//    public void setOnCheckHasGoodsListener(OnCheckHasGoodsListener onCheckHasGoodsListener) {
//        this.onCheckHasGoodsListener = onCheckHasGoodsListener;
//    }

//    public void setOnEditingTvChangeListener(OnEditingTvChangeListener onEditingTvChangeListener) {
//        this.onEditingTvChangeListener = onEditingTvChangeListener;
//    }

//    OnEditingTvChangeListener onEditingTvChangeListener;

//    public void setOnGoodsCheckedChangeListener(OnGoodsCheckedChangeListener onGoodsCheckedChangeListener) {
//        this.onGoodsCheckedChangeListener = onGoodsCheckedChangeListener;
//    }
//
    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }


    public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, HashMap<HeaderModel, List<ChildModel>> listChild) {
//public ExpandListScanAdapter(Context context, List<HeaderModel> listHeader, List<ChildModel> anak) {
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
        return this.listChild.get(listHeaderFilter.get(groupPosition)).size();
//        return this.anak.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeaderFilter.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listChild.get(this.listHeaderFilter.get(groupPosition)).get(childPosition);
//        return this.anak.size();
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // set on click change
        final HeaderModel model = (HeaderModel) getGroup(groupPosition);
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_parent, null);
        }else{
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        HeaderModel headerModel = (HeaderModel) listHeaderFilter.get(groupPosition);
        final String parentNamatoko = headerModel.getNama_toko();


        TextView nama_kk = convertView.findViewById(R.id.txNamaToko);
        TextView no_pelanggan = convertView.findViewById(R.id.tvxIdToko);
        final CheckBox cbparent= convertView.findViewById(R.id.cb_select_parent);


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

        cbparent.setChecked(headerModel.isChecked());
        final boolean nowBeanChecked = headerModel.isChecked();
        cbparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);
            }
        });

        return convertView;
    }


    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d("sholar", listHeaderFilter.get(groupPosition).getId_toko());
        final ChildModel model =  listChild.get(listHeaderFilter.get(groupPosition)).get(childPosition);
        String id_keranjang = model.getId_keranjang();
//        String id_keranjang = "1";
        preferences = new Preferences(context);
        id_konsumen = preferences.getIdKonsumen();
        ChildViewHolder childViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.desain_child, null);
        }

        final ChildModel childModel =  model;

        LinearLayout viewline, increment, decrement;
        final TextView addjumlah, stok, harga, hargaDiskon;
        final ImageView delete_item;
        final CheckBox cbchild;
        TextView nama = convertView.findViewById(R.id.txtnamaPRODUK);
        TextView tvxIdKeranjang = convertView.findViewById(R.id.txtIdkerenjang);
        harga = convertView.findViewById(R.id.txtharga);
        hargaDiskon = convertView.findViewById(R.id.txthargaDiskon);
        final ImageView gambar = convertView.findViewById(R.id.img_gambarkeranjang);
        stok = convertView.findViewById(R.id.txtstock);
        addjumlah = convertView.findViewById(R.id.txt_addjumlah);
        increment = convertView.findViewById(R.id.increment);
        decrement = convertView.findViewById(R.id.decrement);
        delete_item = convertView.findViewById(R.id.cart_delete);
        cbchild = convertView.findViewById(R.id.cb_select_child);

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
//            Toast.makeText(context, "no diskon", Toast.LENGTH_SHORT).show();

        } else {
            double h = vdiskon / 100 * hargaProduk;
            double p = hargaProduk - h;
            double hitung = jumlah * p;
            st = new StringTokenizer(formatRupiah.format(hitung), ",");
            String harganya = st.nextToken().trim();
//            harga.setVisibility(View.GONE);
            hargaDiskon.setPaintFlags(harga.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            hargaDiskon.setTextColor(context.getResources().getColor(R.color.redTransparent));
            hargaDiskon.setTypeface(harga.getTypeface(), Typeface.NORMAL);
            hargaDiskon.setVisibility(View.VISIBLE);
            hargaDiskon.setText(hargajum);
            harga.setText(harganya);

        }

        cbchild.setChecked(childModel.isChecked());
        cbchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean nowBeanChecked = childModel.isChecked();
                childModel.setIsChecked(!nowBeanChecked);

                String harganya = harga.getText().toString();
                String[] nomor = harganya.split("Rp");
                String[] nomor2 = nomor[1].split("\\.");
                String har = "";
                for (int i = 0; i < nomor2.length; i++){
                    har = har+ nomor2[i];
                }
                if(childModel.isChecked()){
                    totot +=  Integer.parseInt(har);
                    Toast.makeText(context, "tot"+totot, Toast.LENGTH_SHORT).show();

                }else{
                    totot -=  Integer.parseInt(har);
                    Toast.makeText(context, "tot"+totot, Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent("custom-message");
                intent.putExtra("total", String.valueOf(totot));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

//                dealPrice();
            }
        });

        cbchild.setChecked(childModel.isChecked());
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
                                int tot = response.body().getJumlah();

                                if (model.getDiskon() == 0) {

                                    st = new StringTokenizer(formatRupiah.format(tot), ",");
                                    String sett = st.nextToken().trim();
                                    harga.setText(sett);
//                                    hargaDiskon.setText(hargajum);

//                                    String hargaaa = harga.getText().toString();
//                                    String[] nomor = hargaaa.split("Rp");
//                                    String[] nomor2 = nomor[1].split("\\.");
//                                    String har = "";
//                                    for (int i = 0; i < nomor2.length; i++){
//                                        har = har+ nomor2[i];
//                                    }
                                    if(childModel.isChecked()){

                                        totot +=  model.getHarga();
                                        Intent intent = new Intent("custom-message");
                                        intent.putExtra("total", String.valueOf(totot));
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                        Toast.makeText(context, "epele"+totot, Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    int hargaProduk = model.getHarga();
                                    int jumlahProduk = Integer.parseInt(String.valueOf(addjumlah.getText()));
                                    int hitung = jumlahProduk* hargaProduk;
                                    double h = potongandiskon / 100 * hargaProduk;
                                    double v = hargaProduk - h;
                                    double p = v * jumlahProduk;
                                    st = new StringTokenizer(formatRupiah.format(p), ",");
                                    st2 = new StringTokenizer(formatRupiah.format(hitung), ",");
                                    String harganya = st.nextToken().trim();
                                    String totalnya = st2.nextToken().trim();
                                    harga.setText(harganya);
                                    hargaDiskon.setText(String.valueOf(totalnya));
//                                    harga.setVisibility(View.GONE);
//                                    String hargaaa = harga.getText().toString();
//                                    String[] nomor = hargaaa.split("Rp");
//                                    String[] nomor2 = nomor[1].split("\\.");
//                                    String har = "";
//                                    for (int i = 0; i < nomor2.length; i++){
//                                        har = har+ nomor2[i];
//                                    }
                                    if(childModel.isChecked()){

                                        totot +=  v;
                                        Intent intent = new Intent("custom-message");
                                        intent.putExtra("total", String.valueOf(totot));
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                        Toast.makeText(context, "epele"+totot, Toast.LENGTH_SHORT).show();
                                    }
                                }






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

        cbchild.setChecked(childModel.isChecked());
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
                            int totot2 = 0;

                            if (response.body() != null && response.isSuccessful()) {
                                int tot = response.body().getJumlah();

                                if (model.getDiskon() == 0) {

                                    st = new StringTokenizer(formatRupiah.format(tot), ",");
                                    String sett = st.nextToken().trim();
                                    harga.setText(sett);
//                                    hargaDiskon.setText(hargajum);

                                    String hargaaa = harga.getText().toString();
                                    String[] nomor = hargaaa.split("Rp");
                                    String[] nomor2 = nomor[1].split("\\.");
                                    String har = "";
                                    for (int i = 0; i < nomor2.length; i++){
                                        har = har+ nomor2[i];
                                    }
                                    if(childModel.isChecked()){

                                        totot -=  model.getHarga();
//                                        Intent intent = new Intent("custom-message");
//                                        intent.putExtra("total", String.valueOf(totot));
//                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                        Toast.makeText(context, "epele"+totot2, Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    int hargaProduk = model.getHarga();
                                    int jumlahProduk = Integer.parseInt(String.valueOf(addjumlah.getText()));
                                    int hitung = jumlahProduk* hargaProduk;
                                    double h = potongandiskon / 100 * hargaProduk;
                                    double v = hargaProduk - h;
                                    double p = v * jumlahProduk;
                                    st = new StringTokenizer(formatRupiah.format(p), ",");
                                    st2 = new StringTokenizer(formatRupiah.format(hitung), ",");
                                    String harganya = st.nextToken().trim();
                                    String totalnya = st2.nextToken().trim();
                                    harga.setText(harganya);
                                    hargaDiskon.setText(String.valueOf(totalnya));
//                                    harga.setVisibility(View.GONE);
                                    String hargaaa = harga.getText().toString();
                                    String[] nomor = hargaaa.split("Rp");
                                    String[] nomor2 = nomor[1].split("\\.");
                                    String har = "";
                                    for (int i = 0; i < nomor2.length; i++){
                                        har = har+ nomor2[i];
                                    }
                                    if(childModel.isChecked()){

                                        totot -=  v;
                                        Intent intent = new Intent("custom-message");
                                        intent.putExtra("total", String.valueOf(totot));
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                        Toast.makeText(context, "epele"+totot, Toast.LENGTH_SHORT).show();
                                    }
                                }



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
        HeaderModel headerModel =  listHeaderFilter.get(groupPosition);
        headerModel.setIsChecked(isChecked);
        for ( int i = 0; i < listChild.get(listHeaderFilter.get(groupPosition)).size(); i++) {
            ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(i);
            childModel.setIsChecked(isChecked);
        }
        notifyDataSetChanged();
//        dealPrice();
    }

    public void setupAllChecked(boolean isChecked) {
        Log.d(TAG, "setupAllChecked: ============");
        Log.d(TAG, "setupAllChecked: isChecked：" + isChecked);

        for (int i = 0; i < listHeaderFilter.size(); i++) {
            HeaderModel headerModel =  listHeaderFilter.get(i);
            headerModel.setIsChecked(isChecked);



            List<ChildModel> childMapList = listChild.get(listHeaderFilter.get(i));

//            Toast.makeText(context, ""+childMapList, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, ""+listChild.get(headerModel).size(), Toast.LENGTH_SHORT).show();
            for (int j = 0; j < childMapList.size(); j++) {
                ChildModel childModel = childMapList.get(j);
                childModel.setIsChecked(isChecked);

            }
        }
        notifyDataSetChanged();
//        dealPrice();
    }


//
//    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
//        Log.d(TAG, "dealOneParentAllChildIsChecked: ============");
//        Log.d(TAG, "dealOneParentAllChildIsChecked: groupPosition：" + groupPosition);
//        // StoreBean storeBean= (StoreBean) parentMapList.get(groupPosition).get("parentName");
////        HashMap<String, List<ChildModel>>  childMapList = (HashMap<String, List<ChildModel>>) listChild.get(groupPosition);
//        for (int j = 0; j < listChild.get(listHeaderFilter.get(groupPosition)).size(); j++) {
//            ChildModel childModel = listChild.get(listHeaderFilter.get(groupPosition)).get(j);
//            if (!childModel.isChecked()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean dealAllParentIsChecked() {
//        Log.d(TAG, "dealAllParentIsChecked: ============");
//        for (int i = 0; i < listHeaderFilter.size(); i++) {
//            HeaderModel headerModel = (HeaderModel) listHeaderFilter.get(i);
//            if (!headerModel.isChecked()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }
//
//    public interface OnEditingTvChangeListener {
//        void onEditingTvChange(boolean allIsEditing);
//    }
//
//    public interface OnGoodsCheckedChangeListener {
//        void onGoodsCheckedChange(int totalCount, double totalPrice);
//    }
//
//    public interface OnCheckHasGoodsListener {
//        void onCheckHasGoods(boolean isHasGoods);
//    }


    class GroupViewHolder {
//        TextView tv_title_parent;
//        TextView id_tv_edit;
        CheckBox cb_select_parent;
    }

    class ChildViewHolder {
//        TextView tv_items_child;
        CheckBox cb_select_child;
//        LinearLayout id_ll_normal;
//        LinearLayout id_ll_edtoring;
//
//        TextView tv_items_child_desc;
//        TextView id_tv_price;
//        TextView id_tv_discount_price;
//        TextView id_tv_count;
//
//        ImageView id_iv_reduce;
//        ImageView id_iv_add;
//        TextView id_tv_des_now;
//        TextView id_tv_count_now;
//        TextView id_tv_price_now;
//        TextView id_tv_goods_star;
//        TextView id_tv_goods_delete;

    }

    public void getTotal() {
//        if (!NetworkUtility.isNetworkConnected(KeranjangDetailActivity.this)){
//            AppUtilits.displayMessage(KeranjangDetailActivity.this,  getString(R.string.network_not_connected));
//
//        }else {
        //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResDetailKeranjang> call = service.getDataDetailKeranjang("konsumen", id_konsumen);
        call.enqueue(new Callback<ResDetailKeranjang>() {
            @Override
            public void onResponse(Call<ResDetailKeranjang> call, retrofit2.Response<ResDetailKeranjang> response) {
                totalHarga = Integer.parseInt(String.valueOf(response.body().getTotalHarganya()));


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


    public void totalHarga(){

    }

}