package com.example.mathpresso.togethermju.core;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.mathpresso.togethermju.model.User;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by choijinjoo on 2016. 10. 25..
 */
public class AppController extends Application {
    private static AppController mInstance;
    private RestManager mRestManager;
    private DatabaseManager mDatabaseManager;
    private SharedPreferences sharedPref;
    private Retrofit mRetrofit;
    public static User user = new User();

    private static final String baseUrl = "http://125.130.223.88:8000/mju/";
    private static final String SP_NAME = "localdb";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sharedPref = getSharedPreferences(SP_NAME,0);

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public Retrofit getRetrofit() {
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

    public DatabaseManager getDatabaseManager() {
        if (mDatabaseManager == null) {
            mDatabaseManager = new DatabaseManager(getApplicationContext());
        }
        return mDatabaseManager;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public String getStringValue(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }
}
