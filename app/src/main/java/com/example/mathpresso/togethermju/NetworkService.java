package com.example.mathpresso.togethermju;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

import static android.renderscript.Element.DataKind.USER;

/**
 * Created by donghyuk on 2016. 11. 17..
 */

public interface NetworkService {
    @POST("/User")
    Call<User> post_user(@Body User user);
}
