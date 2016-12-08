package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.Group;

import com.example.mathpresso.togethermju.model.GroupReply;

import com.example.mathpresso.togethermju.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mk on 2016-11-30.
 */

public interface GroupService {
    @GET("groupview/")
    Call<List<Group>> getNoticeGroup(@Query("id") String id);

    @GET("mygroupview/")
    Call<List<Group>> getJoinGroup(@Query("email") String email);

    @GET("makegroup/")
    Call<DefaultResponse> addGroup(@Query("name") String name,
                                   @Query("notice") String notice,
                                   @Query("introduce") String introduce,
                                   @Query("email") String email
    );

    @GET("getmembers/")
    Call<List<User>> getGroupMember(@Query("gid") String gid);

    @GET("getrecommands/")
    Call<List<User>> getRecommandMember(@Query("id") String id);

    @GET("attendgroup/")
    Call<DefaultResponse> attendgroup(@Query("email") String email, @Query("gid") String id);

    @GET("viewreplys/")
    Call<List<GroupReply>> getReplylist(@Query("gid") String gid);

    @GET("insertreply/")
    Call<DefaultResponse> uploadGroupReply(@Query("email") String email, @Query("gid") String gid, @Query("content") String content);

    @GET("isgroupmember/")
    Call<DefaultResponse> isGroupMember(@Query("email") String email, @Query("gid") String gid);

    @GET("attendgroup/")
    Call<DefaultResponse> attendGroup(@Query("email") String email, @Query("gid") String gid);

}
