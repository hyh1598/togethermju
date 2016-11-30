package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.Notice;
import com.example.mathpresso.togethermju.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by donghyuk on 2016. 11. 30..
 */

public interface UserService {
    //FIXME /noticelist
    @GET("useradd")
    Call<User> getUserInformation(@Query("email")String Email,
                                 @Query("name")String Name,
                                 @Query("rid")String RID,
                                 @Query("birth")String Birth,
                                 @Query("major")String Major,
                                 @Query("password")String Password);

    @GET("userverify")
    Call<User> getUserAuth(@Query("email")String Email,
                           @Query("password")String password);
}