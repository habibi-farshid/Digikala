package com.example.farshid.digikala.ApiService.Interface;

import com.example.farshid.digikala.ApiService.Model.user_responce;

import retrofit2.Call;
import retrofit2.http.GET;

public interface userinterface {

    @GET("test.php")
    Call<user_responce> getUsers();




}
