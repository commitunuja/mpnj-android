package com.sholeh.marketplacenj.adapter.alamat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.DetailAlamat;
import com.sholeh.marketplacenj.activities.UbahPassword;
import com.sholeh.marketplacenj.activities.alamat.PilihAlamatCheckout;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.model.AlamatModel;
import com.sholeh.marketplacenj.respon.ResAlamat;
import com.sholeh.marketplacenj.respon.ResDetailKeranjang;
import com.sholeh.marketplacenj.respon.ResNewPassword;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.Preferences;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.util.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressCheckoutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlamatModel> alamat_models;
    private Context mContext;
    private String TAG = "alamat_adapter";

    //    String  id_alamat;
    Preferences preferences;
    String id_konsumen;


    private ArrayList<CardView> addrlayoutsList = new ArrayList<>();
    private ArrayList<ImageView> imagelist = new ArrayList<>();

    public AddressCheckoutAdapter(Context context, List<AlamatModel> alamatModels) {
        this.alamat_models = alamatModels;
        this.mContext = context;

    }

    private class AlamatItemView extends RecyclerView.ViewHolder {
        TextView tvxidAlamat, tvxnamaCustomer, tvxNoHP, tvxAlamat, tvxKec, tvxKab, tvxProvinsi, tvxKodePos, tvxStatusAlamat;
        ImageView imageselect;
        CardView cvAlamat;


        public AlamatItemView(View itemView) {
            super(itemView);
            tvxnamaCustomer = itemView.findViewById(R.id.tvx_namaCustomer);
            tvxNoHP = itemView.findViewById(R.id.tvx_nohp);
            tvxAlamat = itemView.findViewById(R.id.tvx_alamat);
            tvxKec = itemView.findViewById(R.id.tvx_kec);
            tvxKab = itemView.findViewById(R.id.tvx_kab);
            tvxProvinsi = itemView.findViewById(R.id.tvx_prov);
            tvxKodePos = itemView.findViewById(R.id.tvx_kodepos);
            tvxStatusAlamat = itemView.findViewById(R.id.tvx_statusAlamat);
            imageselect = itemView.findViewById(R.id.imageselect);
            cvAlamat = itemView.findViewById(R.id.cvlistAlamat);


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_alamat, parent, false);
        //  Log.e(TAG, "  view created ");
        return new AlamatItemView(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        //  Log.e(TAG, "bind view "+ position);
        final AlamatModel model = alamat_models.get(position);
        String id_alamat = model.getIdAlamat();
        getDataPref();


        ((AlamatItemView) holder).tvxnamaCustomer.setText(model.getNamaLengkap());
        ((AlamatItemView) holder).tvxNoHP.setText(model.getNomorHP());
        ((AlamatItemView) holder).tvxAlamat.setText(model.getAlamatLengkap());
        ((AlamatItemView) holder).tvxKec.setText("Kec. " + model.getNamaKec());
        ((AlamatItemView) holder).tvxKab.setText("Kab. " + model.getNamaKota());
        ((AlamatItemView) holder).tvxProvinsi.setText("Prov. " + model.getNamaProvinsi());
        ((AlamatItemView) holder).tvxKodePos.setText(model.getKodePos());


        if (model.getStatus().equalsIgnoreCase("utama")) {
            ((AlamatItemView) holder).tvxStatusAlamat.setText("[Utama]");
            ((AlamatItemView) holder).cvAlamat.setBackgroundResource(R.drawable.boarder_green_rounder_white);
            ((AlamatItemView) holder).imageselect.setVisibility(View.VISIBLE);

        } else {
            ((AlamatItemView) holder).cvAlamat.setBackgroundResource(R.drawable.boarder_black_rounder_white);
            ((AlamatItemView) holder).imageselect.setVisibility(View.GONE);
            ((AlamatItemView) holder).tvxStatusAlamat.setVisibility(View.GONE);
        }


        addrlayoutsList.add(((AlamatItemView) holder).cvAlamat);
        imagelist.add(((AlamatItemView) holder).imageselect);

        // ((OrderAddressItemView) holder).address_layoutmain.setTag( model.getaddress_id());
        ((AlamatItemView) holder).cvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Log.e(TAG, "  user select the addres " + model.getaddress_id() );
//                ((AlamatItemView) holder).tvxnamaCustomer.setText(model.getIdAlamat());

                Context context = view.getContext();
                ResDetailKeranjang bs = ((PilihAlamatCheckout) context).getbs();
                ArrayList<String> id = ((PilihAlamatCheckout) context).listIdKeranjang();
                ArrayList<String> idByParent = new ArrayList<String>();


                String namaPenerima = model.getNamaLengkap();
                String AlamatLengkap = model.getAlamatLengkap();
                String kec = model.getNamaKec();
                String kab = model.getNamaKota();
                String prov = model.getNamaProvinsi();
                String pos = model.getKodePos();
                String hp = model.getNomorHP();
                String myaddress = AlamatLengkap + " " + kec + " " + kab + " " + prov + " " + pos + " " + hp;
                String idkec = model.getIdKec();
//                Toast.makeText(mContext, "idKec"+idkec+" alamat "+myaddress, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent("address-checkout");
                intent.putExtra("address", myaddress);
//                intent.putExtra("idkeccheckout",idkec);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

//                Toast.makeText(context, "klik idkeranjang"+id, Toast.LENGTH_SHORT).show();

//                Toast.makeText(mContext, "id alamat " + id_alamat+ " id kec"+idkec, Toast.LENGTH_SHORT).show();
                APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                Call<ResAlamat> call = service.UbahAlamatUtama(id_konsumen, id_alamat);
                call.enqueue(new Callback<ResAlamat>() {
                    @Override
                    public void onResponse(Call<ResAlamat> call, Response<ResAlamat> response) {
                        Log.d("Ubah alamat", String.valueOf(response));
                        if (response.body() != null && response.isSuccessful()) { // true
                            Intent intent = new Intent(mContext, CheckoutActivity.class);
                            intent.putExtra("id_kecpembeli", String.valueOf(model.getIdKec()));
                            intent.putExtra("reset_kurir", "reset");
                            intent.putStringArrayListExtra("idcheckout", id);
                            mContext.startActivity(intent);

                            ((Activity) mContext).finish();
//                        Toast.makeText(mContext, "sukses"+ id_konsumen+" "+id_alamat, Toast.LENGTH_SHORT).show();
                        } else {
//                        Toast.makeText(UbahPassword.this, "Password Gagal di Rubahh"+response.body(), Toast.LENGTH_LONG).show();
//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));


                        }
                    }

                    @Override
                    public void onFailure(Call<ResAlamat> call, Throwable t) {
                        Log.d("gagalubahalamat", String.valueOf(t));
//                    Toast.makeText(UbahPassword.this, "Password Gagal di Ubah"+t, Toast.LENGTH_LONG).show();
                    }
                });

//                ubahAlamatUtama();
//

                //                Intent intent1 = new Intent(mContext, CheckoutActivity.class);
//                intent1.putExtra("alamat_lengkap",myaddress);
//                mContext.startActivity(intent1);

//                ((AlamatActivity) mContext).    addressid = model.getaddress_id();
////
//                for (int i=0; i<addrlayoutsList.size(); i++){
//                    addrlayoutsList.get (i).setBackgroundResource(R.drawable.boarder_black_rounder_white);
//                    imagelist.get(i).setVisibility(View.GONE);
//                }
////
//                ((AlamatItemView) holder).cvAlamat.setBackgroundResource(R.drawable.boarder_green_rounder_white);
//                ((AlamatItemView) holder).imageselect.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return alamat_models.size();
    }

    public void getDataPref() {
        preferences = new Preferences(mContext.getApplicationContext());
        id_konsumen = preferences.getIdKonsumen();

    }

//    public void ubahAlamatUtama() {
//
//
//    }
}
