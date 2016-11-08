package com.mju.project.togethermju;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by choijinjoo on 2016. 10. 25..
 */
public class AppController extends Application {
    private static AppController mInstance;
    private Retrofit mRetrofit;
    private String baseUrl = "우리 서버주소";

    public static synchronized AppController getInstance() {
        return mInstance;
    }
    public Retrofit getRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    return mRetrofit;
    }
}
