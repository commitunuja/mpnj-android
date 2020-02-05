package com.sholeh.marketplacenj.util;

import android.text.TextUtils;

import java.util.regex.Pattern;


public class DataValidation {
    public static String PERSON_FULLNAME= "[a-zA-Z ]*";
    public static String ADDRESS= "[a-zA-Z.+-,0-9 ]*";
    public static String PHONE_NUMBER= "[0-9]*";
    public static String PERSON_EMAIL_ADDRESS = "[a-zA-Z0-9.@-]*";

    public static Boolean isNotValidPassword( String password){
        Boolean valid = true;
        if (!TextUtils.isEmpty(password.trim())){
            if (password.trim().length()>6){
                valid =false;
            }
        }

        return valid;
    }

    public static Boolean isNotValidFullName(String fullname){
        Boolean valid =true;
        if (!TextUtils.isEmpty(fullname.trim())){
            // Log.e("data valid ", " full "+ fullname.trim());
            if (Pattern.compile( PERSON_FULLNAME).matcher(fullname).matches()){
                // Log.e("databb", "match success");
                valid = false;
            }
        }

        return valid;
    }


    public static Boolean isNotValidAddress(String address){
        Boolean valid =true;
        if (!TextUtils.isEmpty(address.trim())){
            if (Pattern.compile( ADDRESS).matcher(address).matches()){
                valid = false;
            }
        }
        return valid;
    }


    public static Boolean isValidPhoneNumber(String phonenumber){
        Boolean valid =true;
        if (!TextUtils.isEmpty(phonenumber.trim()) && phonenumber.length()>15){
            if (Pattern.compile( PHONE_NUMBER).matcher(phonenumber).matches()){
                valid = false;
            }
        }
        return valid;
    }

    public static boolean isNotValidEmail(String emailAddress){
        Boolean valid =true;
        if (!TextUtils.isEmpty(emailAddress.trim())){
            if (Pattern.compile( PERSON_EMAIL_ADDRESS).matcher(emailAddress).matches() && emailAddress.contains("@") && emailAddress.contains(".")){
                valid = false;
            }
        }
        return valid;
    }
}