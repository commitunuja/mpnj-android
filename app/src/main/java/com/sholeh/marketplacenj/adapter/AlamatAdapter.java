package com.sholeh.marketplacenj.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AlamatActivity;
import com.sholeh.marketplacenj.activities.DetailAlamat;
import com.sholeh.marketplacenj.model.AlamatModel;

import java.util.ArrayList;
import java.util.List;

public class AlamatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlamatModel> alamat_models;
    private Context mContext;
    private String TAG = "alamat_adapter";

    private ArrayList<CardView> addrlayoutsList=  new ArrayList<>();
    private ArrayList<ImageView> imagelist = new ArrayList<>();

    public AlamatAdapter (Context context, List<AlamatModel> alamatModels) {
        this.alamat_models = alamatModels;
        this.mContext = context;

    }
    private class AlamatItemView extends RecyclerView.ViewHolder {
        TextView tvxidAlamat, tvxSetAlamat, tvxnamaCustomer,  tvxNoHP, tvxAlamat, tvxKec, tvxKab, tvxProvinsi, tvxKodePos, tvxStatusAlamat ;
        ImageView imageselect;
        CardView cvAlamat;


        public AlamatItemView(View itemView) {
            super(itemView);
            tvxSetAlamat = itemView.findViewById(R.id.tvx_setAlamat);
            tvxnamaCustomer = itemView.findViewById(R.id.tvx_namaCustomer);
            tvxNoHP =  itemView.findViewById(R.id.tvx_nohp);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_alamat, parent,false);
        //  Log.e(TAG, "  view created ");
        return new AlamatItemView(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        //  Log.e(TAG, "bind view "+ position);
        final AlamatModel model =  alamat_models.get(position);



        String noHp = model.getNomorHP();
        String alamatLengkap = model.getAlamatLengkap();
        String Kecamatan = model.getNamaKec();
        String Kota = model.getNamaKota();
        String Provinsi = model.getNamaProvinsi();
        String KodePos = model.getKodePos();
        String alamatgabung = noHp+", "+alamatLengkap+", "+Kecamatan+", "+Kota+", "+Provinsi+", "+"ID "+KodePos;
        ((AlamatItemView) holder).tvxnamaCustomer.setText(model.getNamaLengkap());
        ((AlamatItemView) holder).tvxSetAlamat.setText(alamatgabung);

//        ((AlamatItemView) holder).tvxNoHP.setText(model.getNomorHP());
//        ((AlamatItemView) holder).tvxAlamat.setText(model.getAlamatLengkap());
//        ((AlamatItemView) holder).tvxKec.setText("Kec. "+model.getNamaKec());
//        ((AlamatItemView) holder).tvxKab.setText("Kab. "+model.getNamaKota());
//        ((AlamatItemView) holder).tvxProvinsi.setText("Prov. "+model.getNamaProvinsi());
//        ((AlamatItemView) holder).tvxKodePos.setText(model.getKodePos());




        if (model.getStatus().equalsIgnoreCase("utama")){
            ((AlamatItemView) holder).tvxStatusAlamat.setText("[Utama]");
            ((AlamatItemView) holder).cvAlamat.setBackgroundResource(R.drawable.boarder_green_rounder_white);
            ((AlamatItemView) holder).imageselect.setVisibility(View.GONE);

        }else{
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



                Intent intent1 = new Intent(mContext, DetailAlamat.class);
                intent1.putExtra("alamat_id", model.getIdAlamat());
                mContext.startActivity(intent1);

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
}
