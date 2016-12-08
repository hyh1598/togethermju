package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.DefaultResponse;
import com.example.mathpresso.togethermju.model.User;
import com.squareup.okhttp.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @FormUrlEncoded
    @POST("uploadpic")
    Call<DefaultResponse> uploadProfileImage(@Body okhttp3.RequestBody requestBody);


}