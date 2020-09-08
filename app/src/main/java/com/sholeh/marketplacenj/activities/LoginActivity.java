package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.sholeh.marketplacenj.activities.customview.edittext.EditTextRegular;
import com.sholeh.marketplacenj.activities.dashboard.Homepage;
import com.sholeh.marketplacenj.customview.textview.TextViewRegular;
import com.sholeh.marketplacenj.util.AppUtilits;
import com.sholeh.marketplacenj.util.CONSTANTS;
import com.sholeh.marketplacenj.util.api.APIInterface;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.util.ServiceGenerator;
import com.sholeh.marketplacenj.respon.ResLogin;
import com.sholeh.marketplacenj.util.Preferences;

import java.util.regex.Pattern;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "LoginActivity";

    TextView tv_usernow, tvSignin, tvLupaPass;
    EditText edUserName, edPass;

    Preferences preferences;

    AlertDialog alertDialog;
    AlertDialog alertDialog1;
    private KProgressHUD progressHud;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = new Preferences(this);
        progressHud = KProgressHUD.create(getApplication());
        progress_bar = findViewById(R.id.progress_bar);

        tv_usernow = findViewById(R.id.tvNewUser);
        edUserName = findViewById(R.id.etUsername);
        edPass = findViewById(R.id.etPass);
        tvSignin = findViewById(R.id.tvSignIn);
        tvLupaPass = findViewById(R.id.tvForgetPass);



        tv_usernow.setOnClickListener(this);
        tvSignin.setOnClickListener(this);
        tvLupaPass.setOnClickListener(this);

    }

    private void ProgresDialog() {
        progressHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressHud.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvNewUser:
                Intent pindah = new Intent(this, RegisterActivity.class);
                startActivity(pindah);
                break;
            case R.id.tvSignIn:
                Login();
                break;
            case R.id.tvForgetPass:
                showForgetPassDialog();
//                Intent pindah2 = new Intent(this, ForgotPassword.class);
//                startActivity(pindah2);
                break;

            default:
                break;
        }
    }

    public void Login() {
        final String username_ = edUserName.getText().toString();
        final String password_ = edPass.getText().toString();
        if (!validasi()) return;
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResLogin> userSigninCall = service.loginKonsumenCall(username_, password_);
        userSigninCall.enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                Log.d(TAG, "resLogin" + response.toString());
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getPesan().equalsIgnoreCase("Login Sukses!")) {
                        preferences.saveSPString(Preferences.SP_IdKonsumen, String.valueOf(response.body().getIdKonsumen()));
                        preferences.saveSPString(Preferences.SP_UserName, String.valueOf(response.body().getUsername()));
                        preferences.saveSPString(Preferences.SP_NamaLengkap, String.valueOf(response.body().getNamaLengkap()));
                        preferences.saveSPString(Preferences.SP_NomorHP, String.valueOf(response.body().getNomorHp()));
                        preferences.saveSPString(Preferences.SP_Email, String.valueOf(response.body().getEmail()));
                        preferences.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, true);
                        Toast.makeText(LoginActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Utama.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "User Name dan Password Salah", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User Name dan Password Salah", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Mohon Maaf Terdapat Kesalahan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showForgetPassDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_forgot_password, null);
        dialogBuilder.setView(dialogView);
        ProgressBar progressBar=(ProgressBar)dialogView.findViewById(R.id.progress_bar);


        TextViewRegular tvRequestPasswordReset = (TextViewRegular) dialogView.findViewById(R.id.tvRequestPasswordReset);
        final EditTextRegular etForgetPassEmail = (EditTextRegular) dialogView.findViewById(R.id.etForgetPassEmail);

        alertDialog1 = dialogBuilder.create();
        alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = alertDialog1.getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        alertDialog1.getWindow().setAttributes(lp);
        alertDialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        alertDialog1.show();

        tvRequestPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email_ = etForgetPassEmail.getText().toString();

                if (email_.isEmpty()) {
                    etForgetPassEmail.setError("email tidak boleh kosong");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email_).matches()) {
                    etForgetPassEmail.setError("email tidak valid");
                }else {

                    progressBar.setVisibility(View.VISIBLE);
                    tvRequestPasswordReset.setVisibility(View.GONE);

                    APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
                    Call<ResLogin> userForget= service.requestForgetPass(email_);
                    userForget.enqueue(new Callback<ResLogin>() {
                        @Override
                        public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                            Log.d(TAG, "resLogin" + response.toString());
                            if (response.body() != null && response.isSuccessful()) {
                                if (response.body().getPesan().equalsIgnoreCase("Email tidak ditemukan")){
                                    Toast.makeText(LoginActivity.this, "Email tidak terdaftar", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    tvRequestPasswordReset.setVisibility(View.VISIBLE);
                                }else{
                                    alertDialog1.dismiss();
                                    Toast.makeText(LoginActivity.this, "Konfirmasi passwrod telah dikirim ke alamat email anda", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                    tvRequestPasswordReset.setVisibility(View.VISIBLE);
                                }
//
                            } else {
                                progressBar.setVisibility(View.GONE);
                                tvRequestPasswordReset.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this, "Mohon Maaf Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResLogin> call, Throwable t) {
                            progressBar.setVisibility(View.GONE);
                            tvRequestPasswordReset.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Mohon Maaf Terdapat Kesalahan Jaringan. Silahkan Coba Lagi Nanti", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }




    private boolean validasi() {
        boolean valid = true;
        final String username_ = edUserName.getText().toString();
        final String password_ = edPass.getText().toString();


        if (username_.isEmpty()) {
            edUserName.setError("username wajib di isi");
            valid = false;
        } else {
            edUserName.setError(null);
        }
        if (password_.isEmpty()) {
            edPass.setError("password tidak boleh kosong");
            valid = false;
        } else {
            edPass.setError(null);
        }


        return valid;
    }
}
