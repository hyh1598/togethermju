package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mk on 2016-11-30.
 */

public interface GroupService {
    @GET("groupview/")
    Call<List<Group>> getNoticeGroup(@Query("id")String id);

    @GET("mygroupview/")
    Call<List<Group>> getJoinGroup(@Query("email")String email);


}
