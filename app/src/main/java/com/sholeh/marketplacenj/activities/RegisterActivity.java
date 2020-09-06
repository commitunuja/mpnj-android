package com.sholeh.marketplacenj.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.AccountPicker.AccountChooserOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.sholeh.marketplacenj.activities.checkout.CheckoutActivity;
import com.sholeh.marketplacenj.activities.details.ProductDetailActivity;
import com.sholeh.marketplacenj.activities.transaksi.KonfirmasiPembayaranActivity;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResRegristasi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import static com.google.android.gms.common.AccountPicker.AccountChooserOptions.*;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private String TAG = "RegisterActivity";

    CoordinatorLayout coordinatorLayout;

    EditText ed_nama, ed_username, ed_password, ed_konfirmasiPass, ed_alamat, ed_kodepos, ed_nomorHP, ed_email;
    Button simpank;
    TextView signinHere;

    private static final int REQ_ID = 9001;
    private static final int RESOLVE_HINT = 007;
    Context context;

    int ACCOUNT_PICKER_REQUEST_CODE = 102;

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,16})";


//    Spinner spinProvinsi, spinkota;
//    adapterspin adapterspinner;
//    ArrayList<String> arrayProv = new ArrayList<>();
//    ArrayList<String> listID_prov = new ArrayList<>();
//    ArrayList<String> arrayKota = new ArrayList<>();
//    ArrayList<String> listID_Kota = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//        spinProvinsi = findViewById(R.id.spin_provinsi);
//        spinkota = findViewById(R.id.spin_kota);
        ed_nama = findViewById(R.id.edNama);
        ed_username = findViewById(R.id.edUsername);
        ed_password = findViewById(R.id.edPassword);
        ed_konfirmasiPass = findViewById(R.id.edKonfirmasiPassword);
        ed_alamat = findViewById(R.id.edAlamat);
        ed_kodepos = findViewById(R.id.edKodePos);
        ed_nomorHP = findViewById(R.id.edNomorHP);
        ed_email = findViewById(R.id.edEmail);
        signinHere = findViewById(R.id.signin_here);
//        ed_email.setOnClickListener(this);
        simpank = findViewById(R.id.btnSimpank);
        simpank.setOnClickListener(this);
        ed_email.setOnTouchListener(this);
        ed_nomorHP.setOnTouchListener(this);
        signinHere.setOnClickListener(this);


//        ed_email.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(MotionEvent.ACTION_UP == event.getAction())
//                  getEmail();
//                return false;
//            }
//        });

//        spinProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (spinProvinsi.getSelectedItem().equals("Pilih Provinsi")) {
//
//
//                } else {
//
//                    getCity(listID_prov.get(position));
//
//                }
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spinkota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

//        getProvince();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpank:
                newRegistrasi();
                break;

            case R.id.signin_here:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.edNomorHP:
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

    public void newRegistrasi() {
        final String namalengkap_ = ed_nama.getText().toString();
        final String username_ = ed_username.getText().toString();
        final String password_ = ed_password.getText().toString();
        final String nomorHp_ = ed_nomorHP.getText().toString();
        final String email_ = ed_email.getText().toString();
        if (!validasi()) return;
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResRegristasi> callNewREgistration = service.registerKonsumenCall(
                namalengkap_, username_, password_, nomorHp_, email_);
        callNewREgistration.enqueue(new Callback<ResRegristasi>() {
            @Override
            public void onResponse(Call<ResRegristasi> call, Response<ResRegristasi> response) {



//                String responPesan = response.body().getPesan();

////                Toast.makeText(RegisterActivity.this, "c "+String.valueOf(response.body()), Toast.LENGTH_SHORT).show();
//                Log.d("cekregister", String.valueOf(response.body()));
                if (response.body() != null && response.isSuccessful()) {
//                    Toast.makeText(RegisterActivity.this, "Berhasil "+response.message(), Toast.LENGTH_SHORT).show();

                    if (response.body().getPesan().equalsIgnoreCase("Sukses!")){
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
//                        Toast.makeText(RegisterActivity.this, "ada ", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(DetailAlamat.this, "Alamat Utama Tidak Dapat Dihapus", Toast.LENGTH_LONG).show();
//                        progressDialogHud.dismiss();

                    }else{
                        Toast.makeText(RegisterActivity.this, String.valueOf(response.body().getPesan()), Toast.LENGTH_SHORT).show();
////                        Toast.makeText(DetailAlamat.this, "Alamat berhasil di hapus", Toast.LENGTH_LONG).show();
////                        finish();
////                        progressDialogHud.dismiss();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
//                    AppUtilits.displayMessage(RegisterActivity.this, getString(R.string.failed_request));
                }
            }

            @Override
            public void onFailure(Call<ResRegristasi> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Internet Anda Kurang Stabil. Silahkan Coba Lagi", Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "Koneksi Gagal "+t, Toast.LENGTH_SHORT).show();
//                AppUtilits.displayMessage(RegisterActivity.this, getString(R.string.failed_request));
            }
        });
    }


//    public void getProvince() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIInterface service = retrofit.create(APIInterface.class);
//        Call<ItemProvince> call = service.getProvince();
//
//        call.enqueue(new Callback<ItemProvince>() {
//            @Override
//            public void onResponse(Call<ItemProvince> call, Response<ItemProvince> response) {
//
//                Log.v("wow", "json : " + new Gson().toJson(response));
//
//                if (response.isSuccessful()) {
//
//                    int count_data = response.body().getRajaongkir().getResults().size();
//                    for (int a = 0; a <= count_data - 1; a++) {
//
//                        arrayProv.add(response.body().getRajaongkir().getResults().get(a).getProvince());
//                        listID_prov.add(response.body().getRajaongkir().getResults().get(a).getProvinceId());
//
//
//                    }
//                    adapterspinner = new adapterspin(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item);
//                    adapterspinner.addAll(arrayProv);
//                    adapterspinner.add("Pilih Provinsi");
//                    spinProvinsi.setAdapter(adapterspinner);
//                    spinProvinsi.setSelection(adapterspinner.getCount());
//                } else {
//                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ItemProvince> call, Throwable t) {
//
//                Toast.makeText(RegisterActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

//    public void getCity(String id_province) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(CONSTANTS.URL_RAJAONGKIR)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIInterface service = retrofit.create(APIInterface.class);
//        Call<ItemCity> call = service.getCity(id_province);
//
//        call.enqueue(new Callback<ItemCity>() {
//            @Override
//            public void onResponse(Call<ItemCity> call, Response<ItemCity> response) {
//
//
//                Log.v("wow", "json : " + new Gson().toJson(response));
//
//                if (response.isSuccessful()) {
//
//                    int count_data = response.body().getRajaongkir().getResults().size();
//                    for (int a = 0; a <= count_data - 1; a++) {
//                        com.sholeh.marketplacenj.model.city.Result itemProvince = new com.sholeh.marketplacenj.model.city.Result(
//                                response.body().getRajaongkir().getResults().get(a).getCityId(),
//                                response.body().getRajaongkir().getResults().get(a).getProvinceId(),
//                                response.body().getRajaongkir().getResults().get(a).getProvince(),
//                                response.body().getRajaongkir().getResults().get(a).getType(),
//                                response.body().getRajaongkir().getResults().get(a).getCityName(),
//                                response.body().getRajaongkir().getResults().get(a).getPostalCode()
//
//
//                        );
//
//                        arrayKota.add(response.body().getRajaongkir().getResults().get(a).getType()+" "+response.body().getRajaongkir().getResults().get(a).getCityName());
//                        listID_Kota.add(response.body().getRajaongkir().getResults().get(a).getCityId());
//
//                    }
//
//                    adapterspinner = new adapterspin(RegisterActivity.this, R.layout.support_simple_spinner_dropdown_item);
//                    adapterspinner.addAll(arrayKota);
//                    adapterspinner.add("Pilih Kota");
//                    spinkota.setAdapter(adapterspinner);
//                    spinkota.setSelection(adapterspinner.getCount());
//
//                } else {
//                    String error = "Error Retrive DataProfil from Server !!!";
//                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ItemCity> call, Throwable t) {
//
//                Toast.makeText(RegisterActivity.this, "Message : Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }


//    public void sendRegristasi() {
//        if (!NetworkUtility.isNetworkConnected(RegisterActivity.this)) {
//            AppUtilits.displayMessage(RegisterActivity.this, getString(R.string.network_not_connected));
//
//        }else if (spinProvinsi.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Provinsi")) {
//            Toast.makeText(this, "Provinsi Belum  di Tentukan", Toast.LENGTH_SHORT).show();
//
//        } else if (spinProvinsi.getSelectedItemPosition() < 0 || spinkota.getSelectedItemPosition() < 0 || spinkota.getSelectedItem().toString().trim().equalsIgnoreCase("Pilih Kota")) {
//            Toast.makeText(this, "Kota Belum  di Tentukan", Toast.LENGTH_SHORT).show();
//        } else {
//
//            final String namalengkap_ = ed_nama.getText().toString();
//            final String username_ = ed_username.getText().toString();
//            final String password_ = ed_password.getText().toString();
//            final String konpassword_ = ed_konfirmasiPass.getText().toString();
//            final String idprov_ = listID_prov.get(spinProvinsi.getSelectedItemPosition());
//            final String idkota_ = listID_Kota.get(spinkota.getSelectedItemPosition());
//            final String alamat_ = ed_alamat.getText().toString();
//            final String kodepos_ = ed_kodepos.getText().toString();
//            final String nomorHp_ = ed_nomorHP.getText().toString();
//            final String email_ = ed_email.getText().toString();
//            final String statusA_ = "aktif";

//            if (!validasi()) return;
//            ServiceWrapper serviceWrapper = new ServiceWrapper(null);
//            Call<ResRegristasi> callNewREgistration = serviceWrapper.newUserRegistrationCall(
//                    namalengkap_, username_, password_, idprov_, idkota_, alamat_, kodepos_, nomorHp_, email_, statusA_);
//            callNewREgistration.enqueue(new Callback<ResRegristasi>() {
//                @Override
//                public void onResponse(Call<ResRegristasi> call, Response<ResRegristasi> response) {
//                    Toast.makeText(RegisterActivity.this, "res"+response, Toast.LENGTH_SHORT).show();
//
//                    if (response.body() != null && response.isSuccessful()) {
//                        if (response.body().getPesan().equalsIgnoreCase("Sukses!")) {
//                            for (int a = 0; a < response.body().getData().size() ; a++) {
//                                SharedPreferences preferences = getSharedPreferences("App", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor edit = preferences.edit();
//                                edit.putString("id_konsumen", String.valueOf(response.body().getData().get(a).getIdKonsumen()));
//                                edit.putString("nama_lengkap", String.valueOf(response.body().getData().get(a).getNamaLengkap()));
//
//                                edit.putBoolean("bg",true);
//                                edit.commit();
//                                Toast.makeText(RegisterActivity.this, "id "+String.valueOf(response.body().getData().get(a).getIdKonsumen()), Toast.LENGTH_SHORT).show();
//                                // start home activity
////                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
////                                //intent.putExtra("userid", "sdfsd");
////                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
////                                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                startActivity(intent);
////                                finish();
//                            }
//
//
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "r"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
////                            AppUtilits.displayMessage(RegisterActivity.this,  response.body().getPesan());
//                        }
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "rr"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
//
////                        AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResRegristasi> call, Throwable t) {
//                    Log.e(TAG, " failure " + t.toString());
//                    Toast.makeText(RegisterActivity.this, "rrr"+t, Toast.LENGTH_SHORT).show();
//
//
////                    AppUtilits.displayMessage(RegisterActivity.this,   getString(R.string.failed_request));
//                }
//            });

//        }
//    }

    public void onUsername(View vi) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snack = Snackbar.make(parentLayout, "Selanjutnya klik nomor hp", Snackbar.LENGTH_SHORT);
        View view = snack.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(this,R.color.black));
        view.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        snack.show();
    }

    private void requestHint() {
//        HintRequest hintRequest = new HintRequest.Builder()
//                .setPhoneNumberIdentifierSupported(true)
//                .build();
//
//        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
//                apiClient, hintRequest);
//        startIntentSenderForResult(intent.getIntentSender(),
//                RESOLVE_HINT, null, 0, 0, 0);
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


    public void getNomorHP() {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.CREDENTIALS_API)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) RegisterActivity.this)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) RegisterActivity.this)
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
//                    String newString = mobNumber.replace("+91", "");
                    String newString = mobNumber.replace("+62", "");
                    ed_nomorHP.setText("0"+newString);

                } else {
                    Toast.makeText(this, "err", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    private boolean validasi() {
        boolean valid = true;
        final String namalengkap_ = ed_nama.getText().toString();
        final String username_ = ed_username.getText().toString();
        final String password_ = ed_password.getText().toString();
        final String konpassword_ = ed_konfirmasiPass.getText().toString();
//        final String alamat_ = ed_alamat.getText().toString();
//        final String kodepos_ = ed_kodepos.getText().toString();
        final String nomorHp_ = ed_nomorHP.getText().toString();
        final String email_ = ed_email.getText().toString();
        final String statusA_ = "aktif";


        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");


        if (namalengkap_.isEmpty()) {
            ed_nama.setError("isi nama lengkap anda");
//            Toast.makeText(RegisterActivity.this, "isi nama lengkap anda", Toast.LENGTH_SHORT).show();
            valid = false;
//        } else if (namalengkap_.length() < 2) {
//            Toast.makeText(RegisterActivity.this, "nama lengkap setidaknya minimal 2", Toast.LENGTH_SHORT).show();
//            valid = false;
        } else {
            ed_nama.setError(null);
        }

        if (username_.isEmpty()) {
            ed_username.setError("username wajib di isi");
//            Toast.makeText(RegisterActivity.this, "username wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_username.setError(null);
        }

        if (password_.isEmpty()) {
            ed_password.setError("password wajib di isi");
//            Toast.makeText(RegisterActivity.this, "password wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;

        } else if (password_.length() < 8 || 16 < password_.length()) {
            ed_password.setError("Password panjangnya harus 8-16 karakter, dan mengandung min 1 huruf besar dan 1 huruf kecil karakter");
            valid = false;

        } else if (!UpperCasePatten.matcher(password_).find()) {
            ed_password.setError("Password panjangnya harus 8-16 karakter, dan mengandung min 1 huruf besar dan 1 huruf kecil karakter");
            valid = false;

        } else if (!lowerCasePatten.matcher(password_).find()) {
            ed_password.setError("Password panjangnya harus 8-16 karakter, dan mengandung min 1 huruf besar dan 1 huruf kecil karakter");
            valid = false;
        } else {
            ed_password.setError(null);
        }

        if (konpassword_.isEmpty()) {
            ed_konfirmasiPass.setError("konfirmasi password wajib di isi");
            valid = false;
        } else if (!ed_password.getText().toString().equals(ed_konfirmasiPass.getText().toString())) {
            ed_konfirmasiPass.setError("kata sandi tidak cocok");
            valid = false;
        } else {
             ed_konfirmasiPass.setError(null);
        }

        if (nomorHp_.isEmpty()) {
            ed_nomorHP.setError("nomor hp wajib di isi");
//            Toast.makeText(RegisterActivity.this, "nomor hp wajib di isi", Toast.LENGTH_SHORT).show();
            valid = false;
        } else if (nomorHp_.length() < 12 || 15 < nomorHp_.length()) {
            ed_nomorHP.setError("nomor hp tidak valid");
//            Toast.makeText(RegisterActivity.this, "nomor hp tidak valid", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_nomorHP.setError(null);
        }

        if (email_.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
            ed_email.setError("email tidak valid");
//            Toast.makeText(RegisterActivity.this, "email tidak valid", Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            ed_email.setError(null);
        }
        return valid;
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
