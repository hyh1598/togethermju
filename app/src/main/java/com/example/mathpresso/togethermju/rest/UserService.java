package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.User;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by donghyuk on 2016. 11. 30..
 */

public interface UserService {
    //FIXME /noticelist
    @GET("useradd")
    Call<User> getUserInformation(@Query("email") String Email,
                                  @Query("name") String Name,
                                  @Query("rid") String RID,
                                  @Query("birth") String Birth,
                                  @Query("major") String Major,
                                  @Query("password") String Password);

    @GET("userverify")
    Call<User> getUserAuth(@Query("email") String Email,
                           @Query("password") String password);

    @Multipart
    @POST("uploadpic")
    Call<DefaultResponse> uploadProfileImage(@Query("email") String email, @Part("photo") RequestBody image);


    @Multipart
    @POST("uploadpic")
    Call<DefaultResponse> uploadProfileImage(@Part("photo") okhttp3.RequestBody fi);

    @FormUrlEncoded
    @POST("uploadpic")
    Call<DefaultResponse> uploadProfileImage(@Field("photo") String body);

    // not use
    @GET("")
    Call<User> getUserInformation(@Query("email") String Email,
                                  @Query("name") String Name,
                                  @Query("rid") String RID,
                                  @Query("birth") String Birth,
                                  @Query("major") String Major,
                                  @Query("password") String Password,
                                  @Query("gender") String Gender);


    @GET("useredit")
    Call<User> editUserInformation(@Query("name") String Email,
                                   @Query("major") String Major,
                                   @Query("birth") String Birth,
                                   @Query("gender") String Gender);

    @GET("usereditpassword")
    Call<User> editUserPassword(@Query("password") String Password);
}