package com.sholeh.marketplacenj.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sholeh.marketplacenj.CONSTANTS;
import com.sholeh.marketplacenj.R;


public class AppUtilits {


    public static void displayMessage(Context mContext, String message){

        MessageDialog messageDialog = null;
        if (messageDialog == null)
            messageDialog = new MessageDialog(mContext, message);
        messageDialog.displayMessageShow();

    }


}




