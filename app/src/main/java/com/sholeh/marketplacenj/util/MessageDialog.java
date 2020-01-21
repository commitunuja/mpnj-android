package com.sholeh.marketplacenj.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.sholeh.marketplacenj.R;


public class MessageDialog {

    public AlertDialog alert;

    public MessageDialog(Context mContext, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View g=    inflater.inflate(R.layout.display_message_popup, null);
        TextView txtview = (TextView) g.findViewById(R.id.txt_msg);
        TextView btn_ok = (TextView) g.findViewById(R.id.btn_ok);

        txtview.setText(message);
        builder.setView(g);
        alert = builder.create();
        alert.setCancelable(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });

    }

    public void displayMessageShow(){
        if (alert!=null)
            alert.show();

    }
    public void dislayMessageHide(){
        if (alert!= null)
            alert.dismiss();
    }

}
