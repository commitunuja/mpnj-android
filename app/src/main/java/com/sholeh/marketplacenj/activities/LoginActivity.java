package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.APIInterface;
import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceGenerator;
import com.sholeh.marketplacenj.mfragment.FragmentProfil;
import com.sholeh.marketplacenj.respon.ResLogin;
import com.sholeh.marketplacenj.respon.ResProfil;
import com.sholeh.marketplacenj.respon.ResRegristasi;
import com.sholeh.marketplacenj.respon.ValDataProfil;
import com.sholeh.marketplacenj.util.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sholeh.marketplacenj.util.MyApp.getContext;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "LoginActivity";

    TextView tv_usernow, tvSignin, tvLupaPass;
    EditText edUserName, edPass;


    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = new Preferences(this);


        tv_usernow = findViewById(R.id.tvNewUser);
        edUserName = findViewById(R.id.etUsername);
        edPass = findViewById(R.id.etPass);
        tvSignin = findViewById(R.id.tvSignIn);
        tvLupaPass = findViewById(R.id.tvForgetPass);


        tv_usernow.setOnClickListener(this);
        tvSignin.setOnClickListener(this);
        tvLupaPass.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvNewUser:
                Intent pindah = new Intent(this, RegisterActivity.class);
                startActivity(pindah);
                break;
            case R.id.tvSignIn:
                loginKonsumen();
                break;
            case R.id.tvForgetPass:
                Intent pindah2 = new Intent(this, ForgotPassword.class);
                startActivity(pindah2);
                break;

                default:
                    break;
        }
    }

    public void loginKonsumen(){
        final String username_ = edUserName.getText ().toString ();
        final String password_ = edPass.getText ().toString ();
//        ServiceWrapper serviceWrapper = new ServiceWrapper(null);
        APIInterface service = ServiceGenerator.getRetrofit().create(APIInterface.class);
        Call<ResLogin> userSigninCall = service.loginKonsumenCall(username_, password_);
        userSigninCall.enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {

                Log.d(TAG, "resLogin" + response.toString());
                if (response.body() != null && response.isSuccessful()) {

                    if (response.body().getPesan().equalsIgnoreCase("Login Sukses!")){

                       preferences.saveSPString(Preferences.SP_IdKonsumen,response.body().getIdKonsumen());
                       preferences.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, true);



                        Toast.makeText(LoginActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,Utama.class);
                        startActivity(intent);
                        finish();


                    }else{
                        Toast.makeText(LoginActivity.this, "User Name dan Password Salah"+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this, "User Name dan Password Salah"+response.body(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
