package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
public interface NoticeService {
    //FIXME /noticelist
    @GET("noticelist/2/")
    Call<List<Notice>> getNotices();

    @GET("watchednotice/")
    Call<List<Notice>> getWatchednotices(@Query("email")String email);

}
