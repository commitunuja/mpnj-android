package com.sholeh.marketplacenj.activities.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sholeh.marketplacenj.R;
import com.sholeh.marketplacenj.activities.AddAlamat;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvxtolbar, tvxUbahAlamat, tvxSetAlamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        tvxtolbar = findViewById(R.id.txt_toolbarKeranjang);
        tvxUbahAlamat = findViewById(R.id.tvxubahAlamat);
        tvxSetAlamat = findViewById(R.id.tvx_setAlamat);
        tvxtolbar.setText("Checkout");

        tvxUbahAlamat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvxubahAlamat:
                startActivity(new Intent(this, AddAlamat.class));
            break;
            default:
                break;
        }
    }
}
