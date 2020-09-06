package com.sholeh.marketplacenj.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.snackbar.Snackbar;
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

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class AkunActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener,  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    EditText ed_nama;
    TextView ed_nohp, ed_email, btn_UpdateProfil, btn_Batal, ed_username, tvx_titleToolbar;


    private ResProfil tvDataProfil;
    Preferences preferences;
    String id_konsumen;
    CircleImageView imgAkun;
    ImageView imgBack;
    private KProgressHUD progressDialogHud;


    private static final int REQ_ID = 9001;
    private static final int RESOLVE_HINT = 007;

    TextView tvx_title;
    ImageView imgtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);
        preferences = new Preferences(getApplication());
        id_konsumen = preferences.getIdKonsumen();
        progressDialogHud = KProgressHUD.create(AkunActivity.this);
        imgtoolbar = findViewById(R.id.imgtoolbarF);
        tvx_title = findViewById(R.id.title);
        tvx_title.setText("Ubah Profil");
        tvx_title.setTextColor(getResources().getColor(R.color.white));
        tvx_title.setVisibility(View.VISIBLE);
        imgtoolbar.setOnClickListener(this);


        ed_username = findViewById(R.id.edUsername);
        ed_nama = findViewById(R.id.edNama);
        ed_nohp = findViewById(R.id.edNohp);
        ed_email = findViewById(R.id.edEmail);
        imgAkun = findViewById(R.id.profile_image);
        imgBack = findViewById(R.id.imgtoolbarF);
        tvx_titleToolbar = findViewById(R.id.edit_txt);
        btn_UpdateProfil = findViewById(R.id.btnUpdateProfil);
        btn_Batal = findViewById(R.id.tvBatal);
        btn_UpdateProfil.setOnClickListener(this);
        btn_Batal.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        ed_nohp.setOnTouchListener(this);
        ed_email.setOnTouchListener(this);

        getDataProfi();
        tvx_titleToolbar.setText("Ubah Profil");
        tvx_titleToolbar.setTextColor(getResources().getColor(R.color.white));

    }

    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpdateProfil:
                updateProfil();
                break;

            case R.id.tvBatal:
                finish();
                break;

            case  R.id.imgtoolbarF:
                finish();
                break;

            default:
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edNohp:
                if (MotionEvent.ACTION_UP == event.getAction())
                    getNomorHP();
                break;


            case R.id.edEmail:
                if (MotionEvent.ACTION_UP == event.getAction())
                    getEmail();
                break;
            default:
                break;
        }
        return false;
    }

    public void onNama(View vi) {
//        View parentLayout = findViewById(android.R.id.content);
//        Snackbar snack = Snackbar.make(parentLayout, "Selanjutnya klik nomor hp", Snackbar.LENGTH_SHORT);
//        View view = snack.getView();
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        view.setLayoutParams(params);
//        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
//        tv.setTextColor(ContextCompat.getColor(this,R.color.black));
//        view.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
//        snack.show();
    }


    public void getNomorHP() {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) AkunActivity.this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) AkunActivity.this)
                .build();
        googleApiClient.connect();
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();
        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ID && resultCode == RESULT_OK) {
//            Toast.makeText(context, "s", Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, "email ", Toast.LENGTH_SHORT).show();
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            ed_email.setText(String.valueOf(accountName));

//                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            GoogleSignInAccount acct = result.getSignInAccount();
//
//            String personName = acct.getDisplayName();
//            String email = acct.getEmail();
//            ed_email.setText(String.valueOf(email));


        }
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                com.google.android.gms.auth.api.credentials.Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                if (credential != null) {
                    String mobNumber = credential.getId();
                    String newString = mobNumber.replace("+91", "");
                    ed_nohp.setText(newString);

                } else {
                    Toast.makeText(this, "err", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void getEmail() {
//        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
//                false, null, null, null, null);
//        startActivityForResult(intent, REQ_ID);
//
//        String[] accountTypes = new String[]{"com.google"};
//        Intent intent = AccountPicker.zza(null, null, accountTypes, false, null, null, null, null, false, 1, 0);
//        startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);

        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
                new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE},
                false, null, null, null, null).
                putExtra("overrideTheme", 1);
        startActivityForResult(intent, REQ_ID);


//
//        String[] accountTypes = new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE};
//        Intent intent = AccountPicker.newChooseAccountIntent(null, null, accountTypes, false, null, null, null, null);
//
////        AccountPicker.zza(null, null,
////                accountTypes, false, null, null, null, null, false, 1, 0);
//        // set the style
//        int zza ;
//        if ( isItDarkTheme ) {
//            intent.putExtra("overrideTheme", 0);
//        } else {
//            intent.putExtra("overrideTheme", 1);
//        }
//        intent.putExtra("overrideCustomTheme", 0);


//        Intent googlePicker = AccountPicker.newChooseAccountIntent(null, null, new String[]{GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE}, true, null, null, null, null);
//        startActivityForResult(googlePicker, REQ_ID);


//        getEmailId(getApplicationContext())

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

    public void getDataProfi() {

        if (!NetworkUtility.isNetworkConnected(AkunActivity.this)) {
            AppUtilits.displayMessage(AkunActivity.this, getString(R.string.network_not_connected));

        } else {

            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResProfil> call = service.getDataProfil(id_konsumen);
            call.enqueue(new Callback<ResProfil>() {
                @Override
                public void onResponse(Call<ResProfil> call, Response<ResProfil> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        tvDataProfil = response.body();
                        String userName = tvDataProfil.getData().getUsername();
                        String namaLengkap = tvDataProfil.getData().getNamaLengkap();
                        String noHP = tvDataProfil.getData().getNomorHp();
                        String email = tvDataProfil.getData().getEmail();
                        ed_username.setText(userName);
                        ed_nama.setText(namaLengkap);
                        ed_nohp.setText(noHP);
                        ed_email.setText(email);
                        Picasso.with(getContext()).load(CONSTANTS.BASE_URL + "assets/foto_profil_konsumen/" + tvDataProfil.getData().getFotoProfil()).into(imgAkun);
                        progressDialogHud.dismiss();
                    } else {
                        AppUtilits.displayMessage(AkunActivity.this, "Terdapat Kesalahan. Silahkan Coba Lagi Nanti.");
                        progressDialogHud.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<ResProfil> call, Throwable t) {
                    AppUtilits.displayMessage(AkunActivity.this, getString(R.string.network_not_connected));
                    progressDialogHud.dismiss();

//                    Toast.makeText(AkunActivity.this, "no connection" + t, Toast.LENGTH_LONG).show();

                    //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
                }
            });
        }
    }


    public void updateProfil() {

        if (!NetworkUtility.isNetworkConnected(AkunActivity.this)) {
            AppUtilits.displayMessage(AkunActivity.this, getString(R.string.network_not_connected));

        } else {
//            if (!validasi()) return;
            ProgresDialog();
            APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
            Call<ResRegristasi> call = service.updateKonsumen(id_konsumen, ed_nama.getText().toString(), ed_nohp.getText().toString(), ed_email.getText().toString(), "aktif");

            call.enqueue(new Callback<ResRegristasi>() {
                @Override
                public void onResponse(Call<ResRegristasi> call, Response<ResRegristasi> response) {


                    if (response.body() != null && response.isSuccessful()) { // true
                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {
                            Toast.makeText(AkunActivity.this, "Berhasil diperbarui", Toast.LENGTH_LONG).show();
                            progressDialogHud.dismiss();
                            finish();
////
////
                        } else {
                            Toast.makeText(AkunActivity.this, "Gagal diperbarui", Toast.LENGTH_LONG).show();
                            progressDialogHud.dismiss();

//                            AppUtilits.displayMessage(UbahPassword.this,  response.body().getPesan());
                        }
                    } else {
                        Toast.makeText(AkunActivity.this, "Gagal diperbarui", Toast.LENGTH_LONG).show();
                        progressDialogHud.dismiss();

//                        AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));

                    }
                }

                @Override
                public void onFailure(Call<ResRegristasi> call, Throwable t) {
                    AppUtilits.displayMessage(AkunActivity.this, getString(R.string.network_not_connected));
                    progressDialogHud.dismiss();

//                    Toast.makeText(AkunActivity.this, "Gagal diperbarui"+t, Toast.LENGTH_LONG).show();

                    //  Log.e(TAG, " failure "+ t.toString());
//                    AppUtilits.displayMessage(UbahPassword.this,  getString(R.string.failed_request));
                }
            });
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
