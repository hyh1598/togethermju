package com.example.mathpresso.togethermju.core;

import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

import com.example.mathpresso.togethermju.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by choijinjoo on 2016. 10. 25..
 */
public class AppController extends MultiDexApplication {
    private static AppController mInstance;
    private RestManager mRestManager;
    private DatabaseManager mDatabaseManager;
    private SharedPreferences sharedPref;
    private Retrofit mRetrofit;
    public static User user = new User();

    public static String getBaseUrl() {
        return baseUrl;
    }

    private static final String baseUrl = "http://125.130.223.88:8000/mju/";
    private static final String SP_NAME = "localdb";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        sharedPref = getSharedPreferences(SP_NAME, 0);

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public Retrofit getRetrofit() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(jacksonConverterFactory)
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

    public void clearLocalStore() {
        SharedPreferences.Editor spEditor = sharedPref.edit();
        spEditor.remove("email");
        spEditor.remove("rid");
        spEditor.remove("name");
        spEditor.remove("major");
        spEditor.remove("pic");
        spEditor.commit();
    }

    //db에서 최근 데이터 가져오기
    public static boolean UpdateUserinfo(AppController appController){
        User userinfo= new User();

        String email = appController.getStringValue("email","");//email을 통해 검증
        if(email.equals(""))
            return false;
        userinfo.setEmail(email);
        userinfo.setName(appController.getStringValue("name",""));
        userinfo.setMajor(appController.getStringValue("major",""));
        userinfo.setRid(appController.getStringValue("rid",""));
        userinfo.setPic(appController.getStringValue("pic",""));
        AppController.user = userinfo;
        return true;
    }
    //데이터 저장하기
    public static void setUserinfo(AppController appController){
        /*data base 에 유저정보 저장*/
        appController.setString("name",user.getName());
        appController.setString("rid",user.getRid());
        appController.setString("email",user.getEmail());
        appController.setString("major",user.getEmail());
        appController.setString("pic",user.getPic());
            /*
           String rid;
            String email;
            String password;
            String gender;
            String birth;
            String major;
            String name;
            */
    }

}
