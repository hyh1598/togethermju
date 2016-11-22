package com.example.mathpresso.togethermju.core;
import com.example.mathpresso.togethermju.rest.NetworkService;
import com.example.mathpresso.togethermju.rest.NoticeService;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
public class RestManager {
    NoticeService noticeService = AppController.getInstance().getRetrofit().create(NoticeService.class);
    NetworkService networkService = AppController.getInstance().getRetrofit().create(NetworkService.class);

    public NoticeService getNoticeService() { return noticeService; }
    public NetworkService getNetworkService() { return networkService;}
}