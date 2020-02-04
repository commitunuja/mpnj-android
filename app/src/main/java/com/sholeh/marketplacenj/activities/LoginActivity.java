package com.sholeh.marketplacenj.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.ServiceWrapper;
import com.sholeh.marketplacenj.respon.ResLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "LoginActivity";

    TextView tv_usernow, tvSignin;
    EditText edUserName, edPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_usernow = findViewById(R.id.tvNewUser);
        edUserName = findViewById(R.id.etUsername);
        edPass = findViewById(R.id.etPass);
        tvSignin = findViewById(R.id.tvSignIn);


        tv_usernow.setOnClickListener(this);
        tvSignin.setOnClickListener(this);

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

                default:
                    break;
        }
    }

    public void loginKonsumen(){
        final String username_ = edUserName.getText ().toString ();
        final String password_ = edPass.getText ().toString ();
        ServiceWrapper serviceWrapper = new ServiceWrapper(null);
        Call<ResLogin> userSigninCall = serviceWrapper.KonsumenSigninCall(username_, password_);
        userSigninCall.enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                Log.d(TAG, "resLogin" + response.toString());
                if (response.body() != null && response.isSuccessful()) {
                    if (response.body().getPesan().equalsIgnoreCase("Login Sukses!")){
                        Toast.makeText(LoginActivity.this, "Berhasil Guys", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Gagal Guys", Toast.LENGTH_SHORT).show();
                    }

                }else {

                    Toast.makeText(LoginActivity.this, "gagal"+response, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Try Again"+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
