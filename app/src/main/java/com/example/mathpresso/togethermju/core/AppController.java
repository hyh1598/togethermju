package com.example.mathpresso.togethermju.core;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.multidex.MultiDexApplication;

import com.example.mathpresso.togethermju.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    public static final String APIKEY ="AIzaSyCoQG3LIHt0zeWIjTkCWjlls3x5wSccfF8";
    public static final String PROJECTNUM = "334320554531";
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
        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(INTERCEPTOR)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                .addConverterFactory(jacksonConverterFactory)
                .build();

        return mRetrofit;
    }

    private final Interceptor INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            builder.addHeader("accept", "application/json");
            builder.addHeader("Content-Type", "application/json");
            Request request = builder.build();
            Response response = chain.proceed(request);
            return response;
        }
    };

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
    public static boolean UpdateUserinfo(AppController appController) {
        User userinfo = new User();

        String email = appController.getStringValue("email", "");//email을 통해 검증
        if (email.equals(""))
            return false;
        userinfo.setEmail(email);
        userinfo.setName(appController.getStringValue("name",""));
        userinfo.setMajor(appController.getStringValue("major",""));
        userinfo.setRid(appController.getStringValue("rid",""));
        userinfo.setGender(appController.getStringValue("gender",""));
        userinfo.setBirth(appController.getStringValue("birth",""));
        userinfo.setPassword(appController.getStringValue("password",""));

        AppController.user = userinfo;
        return true;
    }

    //데이터 저장하기
    public static void setUserinfo(AppController appController) {
        /*data base 에 유저정보 저장*/

        appController.setString("name",user.getName());
        appController.setString("rid",user.getRid());
        appController.setString("email",user.getEmail());
        appController.setString("major",user.getMajor());
        appController.setString("gender",user.getGender());
        appController.setString("birth",user.getBirth());
        appController.setString("password",user.getPassword());

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
