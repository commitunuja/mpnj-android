package com.sholeh.marketplacenj.adapter;

import android.content.Context;
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
        TextView tvxnamaCustomer,  tvxNoHP, tvxAlamat, tvxKec, tvxKab, tvxProvinsi, tvxKodePos, tvxStatusAlamat ;
        ImageView imageselect;
        CardView cvAlamat;


        public AlamatItemView(View itemView) {
            super(itemView);
            tvxnamaCustomer = itemView.findViewById(R.id.tvx_namaCustomer);
            tvxNoHP =  itemView.findViewById(R.id.tvx_nohp);
            tvxAlamat = itemView.findViewById(R.id.tvx_alamat);
            tvxKec = itemView.findViewById(R.id.tvx_kec);
            tvxKab = itemView.findViewById(R.id.tvx_kab);
            tvxProvinsi = itemView.findViewById(R.id.tvx_prov);
            tvxKodePos = itemView.findViewById(R.id.tvx_kodepos);

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

        ((AlamatItemView) holder).tvxnamaCustomer.setText(model.getNamaLengkap());
        ((AlamatItemView) holder).tvxNoHP.setText(model.getNomorHP());
        ((AlamatItemView) holder).tvxAlamat.setText(model.getAlamatLengkap());
        ((AlamatItemView) holder).tvxKab.setText(model.getNamaKota());
        ((AlamatItemView) holder).tvxProvinsi.setText(model.getNamaProvinsi());
        ((AlamatItemView) holder).tvxKodePos.setText(model.getKodePos());

        addrlayoutsList.add(((AlamatItemView) holder).cvAlamat);
        imagelist.add(((AlamatItemView) holder).imageselect);

        // ((OrderAddressItemView) holder).address_layoutmain.setTag( model.getaddress_id());
        ((AlamatItemView) holder).cvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "klik", Toast.LENGTH_SHORT).show();

                //   Log.e(TAG, "  user select the addres " + model.getaddress_id() );

//                (( OrderAddressActivity) mContext).addressid = model.getaddress_id();
//
//                for (int i=0; i<addrlayoutsList.size(); i++){
//                    addrlayoutsList.get (i).setBackgroundResource(R.drawable.boarder_black_rounder_white);
//                    imagelist.get(i).setVisibility(View.GONE);
//                }
//
//                ((OrderAddressItemView) holder).address_layoutmain.setBackgroundResource(R.drawable.boarder_green_rounder_white);
//                ((OrderAddressItemView) holder).imageselect.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return alamat_models.size();
    }
}
