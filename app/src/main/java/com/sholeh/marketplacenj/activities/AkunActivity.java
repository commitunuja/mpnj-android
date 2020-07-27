package com.sholeh.marketplacenj.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.activities.alamat.PilihAlamatCheckout;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.NetworkUtility;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.util.Preferences;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class AkunActivity extends AppCompatActivity implements View.OnClickListener {
    EditText  ed_nama;
    TextView ed_nohp, ed_email, btn_UpdateProfil, btn_Batal, ed_username;


    private ResProfil tvDataProfil;
    Preferences preferences;
    String id_konsumen;
    ImageView imgAkun, imgBack;
    private KProgressHUD progressDialogHud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        progressDialogHud = KProgressHUD.create(AkunActivity.this);


        ed_username = findViewById(R.id.edUsername);
        ed_nama = findViewById(R.id.edNama);
        ed_nohp = findViewById(R.id.edNohp);
        ed_email = findViewById(R.id.edEmail);
        imgAkun = findViewById(R.id.profile_image);
        imgBack = findViewById(R.id.imgBackProfil);
        btn_UpdateProfil = findViewById(R.id.btnUpdateProfil);
        btn_Batal = findViewById(R.id.tvBatal);
        btn_UpdateProfil.setOnClickListener(this);
        btn_Batal.setOnClickListener(this);
        imgBack.setOnClickListener(this);

        getDataProfi();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdateProfil:
                updateProfil();
                break;

            case R.id.tvBatal:
                finish();
                break;
            case R.id.imgBackProfil:
                finish();
                break;

                default:
                    break;
        }
    }
    private void ProgresDialog(){
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
    }

    public void getDataProfi(){

        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResProfil> call = service.getDataProfil(id_konsumen);

        call.enqueue(new Callback<ResProfil>() {
            @Override
            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {

                tvDataProfil = response.body();
                String userName = tvDataProfil.getData().getUsername();
                String namaLengkap = tvDataProfil.getData().getNamaLengkap();
                String noHP = tvDataProfil.getData().getNomorHp();
                String email = tvDataProfil.getData().getEmail();
                ed_username.setText(userName);
                ed_nama.setText(namaLengkap);
                ed_nohp.setText(noHP);
                ed_email.setText(email);
                Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/"+tvDataProfil.getData().getFotoProfil()).into(imgAkun);


            }

            @Override
            public void onFailure(Call<ResProfil> call, Throwable t) {
                Toast.makeText(AkunActivity.this, "no connection"+t, Toast.LENGTH_LONG).show();

                //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
            }
        });
    }


    public void updateProfil() {

        if (!NetworkUtility.isNetworkConnected(AkunActivity.this)){
            AppUtilits.displayMessage(AkunActivity.this,  getString(R.string.network_not_connected));

        }else {
//            if (!validasi()) return;
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResRegristasi> call = service.updateKonsumen(id_konsumen,ed_nama.getText().toString(), ed_nohp.getText().toString(), ed_email.getText().toString(),"aktif" );

            call.enqueue(new Callback<ResRegristasi>() {
                @Override
                public void onResponse(Call<ResRegristasi> call, Response<ResRegristasi> response) {


                    if (response.body()!= null && response.isSuccessful()){ // true
                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")){
                            Toast.makeText(AkunActivity.this, "Berhasil diperbarui", Toast.LENGTH_LONG).show();
                            progressDialogHud.dismiss();
                            finish();
////
////
                        }else {
                            Toast.makeText(AkunActivity.this, "Gagal diperbarui", Toast.LENGTH_LONG).show();
                            progressDialogHud.dismiss();

//                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
                        }
                    }else {
                        Toast.makeText(AkunActivity.this, "Gagal diperbarui", Toast.LENGTH_LONG).show();
                        progressDialogHud.dismiss();

//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));

                    }
                }

                @Override
                public void onFailure(Call<ResRegristasi> call, Throwable t) {
                    AppUtilits.displayMessage(AkunActivity.this,  getString(R.string.network_not_connected));
                    progressDialogHud.dismiss();

//                    Toast.makeText(AkunActivity.this, "Gagal diperbarui"+t, Toast.LENGTH_LONG).show();

                    //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
                }
            });
        }
    }

//    public void getData(){
//
//        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
//        Call<ResProfil> call = service.getDataProfil(id_konsumen);
//
//        call.enqueue(new Callback<ResProfil>() {
//            @Override
//            public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {
//                tvDataProfil = response.body();
//
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResProfil> call, Throwable t) {
//                Toast.makeText(AkunActivity.this, "no connection"+t, Toast.LENGTH_SHORT).show();
//
//                //  Log.e(TAG, " failure "+ t.toString());
////                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
//            }
//        });
//    }
}
