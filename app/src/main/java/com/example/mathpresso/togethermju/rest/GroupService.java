package com.example.mathpresso.togethermju.rest;

import com.example.mathpresso.togethermju.model.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mk on 2016-11-30.
 */

public interface GroupService {
    //FIXME /noticelist
    @GET(" ")
    Call<List<Notice>> getNotices();



}
