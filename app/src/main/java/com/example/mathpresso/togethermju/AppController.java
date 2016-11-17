package com.example.mathpresso.togethermju;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by choijinjoo on 2016. 10. 25..
 */
public class AppController extends Application {
    private static AppController mInstance;
    private RestManager mRestManager;
    private Retrofit mRetrofit;
    private String baseUrl = "http://125.130.223.88:8000/mju/";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

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
    public RestManager getRestManager() {
        if (mRestManager == null) {
            mRestManager = new RestManager();
        }
        return mRestManager;
    }

}
