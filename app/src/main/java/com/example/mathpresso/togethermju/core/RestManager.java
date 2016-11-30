package com.example.mathpresso.togethermju.core;
import com.example.mathpresso.togethermju.rest.NetworkService;
import com.example.mathpresso.togethermju.rest.NoticeService;
import com.example.mathpresso.togethermju.rest.UserService;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
public class RestManager {
    NoticeService noticeService = AppController.getInstance().getRetrofit().create(NoticeService.class);
    NetworkService networkService = AppController.getInstance().getRetrofit().create(NetworkService.class);
    UserService userService = AppController.getInstance().getRetrofit().create(UserService.class);
    public UserService getUserService() { return userService; }
    public NoticeService getNoticeService() { return noticeService; }
    public NetworkService getNetworkService() { return networkService;}

}
