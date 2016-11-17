package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by donghyuk on 2016. 11. 17..
 */

public interface NetworkService {
    @POST("/User")
    Call<User> post_user(@Body User user);
}
