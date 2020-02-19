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
import com.sholeh.marketplacenj.respon.ResLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "LoginActivity";

    TextView tv_usernow, tvSignin, tvLupaPass;
    EditText edUserName, edPass;

    SharedPreferences preferences;
    SharedPreferences.Editor input;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences("App",Context.MODE_PRIVATE);

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
                        Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        input = preferences.edit();
                        input.putString(CONSTANTS.ID_KONSUMEN, String.valueOf(response.body().getIdKonsumen()));
                        input.putBoolean("aktif", true);
                        input.commit();

                        Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        finish();


                    }else{
                        Toast.makeText(LoginActivity.this, "User Name dan Password Salah", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginActivity.this, "User Name dan Password Salah", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
