package com.sholeh.marketplacenj.util;

import android.content.Context;


public class AppUtilits {
    public static void displayMessage(Context mContext, String message){

        MessageDialog messageDialog = null;
        if (messageDialog == null)
            messageDialog = new MessageDialog(mContext, message);
        messageDialog.displayMessageShow();

    }


}




