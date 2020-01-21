package com.sholeh.marketplacenj.util;

import android.content.Context;
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

    public static void UpdateCartCount(Menu mainmenu){ // cart menu item.xml
        MenuItem item = mainmenu.findItem(R.id.menu_keranjang);
        Log.e("apputil ", " menu title "+ item.getTitle() );

        TextView cartcount =   item.getActionView().findViewById(R.id.cart_count);
        cartcount.setText( String.valueOf(SharePreferenceUtils.getInstance().getInteger(CONSTANTS.CART_ITEM_COUNT)));

        if (SharePreferenceUtils.getInstance().getInteger(CONSTANTS.CART_ITEM_COUNT)>0){
            cartcount.setVisibility(View.VISIBLE);
        }else {
            cartcount.setVisibility(View.GONE);
        }

    }

}




