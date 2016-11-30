package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by choijinjoo on 2016. 11. 17..
 */
public interface NoticeService {

    @GET("watchednotice/")
    Call<List<Notice>> getWatchednotices(@Query("email") String email);

    @GET("noticeview/")
    Call<List<Notice>> getNotices(@Query("type") String type);

    @GET("watchnotice/")
    Call<DefaultResponse> watchNotice(@Query("email") String email, @Query("id") String id);

    @GET("checkwatch/")
    Call<DefaultResponse> checkWatch(@Query("email") String email, @Query("id") String id);


}
