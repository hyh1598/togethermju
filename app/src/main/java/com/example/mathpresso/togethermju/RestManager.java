package com.example.mathpresso.togethermju;

import com.example.mathpresso.togethermju.rest.NoticeService;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
public class RestManager {
    NoticeService NoticeService = AppController.getInstance().getRetrofit().create(NoticeService.class);

    public NoticeService getNoticeService() { return NoticeService; }
}
