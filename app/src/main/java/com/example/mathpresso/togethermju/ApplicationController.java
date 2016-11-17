package com.example.mathpresso.togethermju;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by donghyuk on 2016. 11. 17..
 */

public class ApplicationController extends Application {
    public static ApplicationController instance ;
    public static ApplicationController applicationController;
    public static NetworkService networkService;
    public static ApplicationController getInstance() { return instance ; }
    public static User user = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this; //어플리케이션이 처음 실행될 때 인스턴스를 생성합니다.
    }


    public NetworkService getNetworkService() { return networkService; }

    private String baseUrl;

    public void buildNetworkService(String ip, int port) {
        synchronized (ApplicationController.class) {
            if (networkService == null) {
                baseUrl = String.format("http://%s:%d", ip, port);
                Gson gson = new GsonBuilder()
                        .create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);
                //서버에서 json 형식으로 데이터를 보내고 이를 파싱해서 받아오기 위해서 사용합니다.

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(factory)
                        .build();
                networkService = retrofit.create(NetworkService.class);
            }
        }
    }
}
