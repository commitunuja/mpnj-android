package com.sholeh.marketplacenj;

import android.util.Log;
import android.widget.Toast;

import okhttp3.Interceptor;
import retrofit2.Retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sholeh.marketplacenj.model.ValueReg;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceWrapper {
    private APIInterface mServiceInterface;

    public ServiceWrapper(Interceptor mInterceptorheader) {
        mServiceInterface = getRetrofit(mInterceptorheader).create(APIInterface.class);
    }

    public Retrofit getRetrofit(Interceptor mInterceptorheader) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mOkHttpClient = null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONSTANTS.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(CONSTANTS.API_READ_TIMEOUT, TimeUnit.SECONDS);

//        if (BuildConfig.DEBUG)
//            builder.addInterceptor(loggingInterceptor);

        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }


        mOkHttpClient = builder.build();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient)
                .build();
        return retrofit;
    }

    public Call<ValueReg> newUserRegistrationCall(String namaLengkap, String username, String password,
                                                  String provinsiId, String cityId, String alamat,
                                                  String kodePos, String nomorHp, String email, String status) {
        return mServiceInterface.registerKonsumenCall(
                                                    convertPlainString(namaLengkap), convertPlainString(username),
                                                    convertPlainString(password), convertPlainString(provinsiId), convertPlainString(cityId),
                                                    convertPlainString(alamat), convertPlainString(kodePos), convertPlainString(nomorHp),
                                                    convertPlainString(email), convertPlainString(status));
    }


    // convert aa param into plain text
    public RequestBody convertPlainString(String data) {
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return plainString;
    }
}
