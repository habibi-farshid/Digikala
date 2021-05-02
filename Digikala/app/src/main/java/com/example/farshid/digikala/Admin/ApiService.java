package com.example.farshid.digikala.Admin;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("Register.php")
    @FormUrlEncoded
    Call<String> savePost(@Field("Email") String title,
                          @Field("Password") String body);
    @GET("Admin_insertpost.php")
    Call<String> givedata(@Query("Name") String Name,
                          @Query("En_Name") String En_Name,
                          @Query("PrePrice") String PrePrice,
                          @Query("Price") String Price,
                          @Query("Color") String Color,
                          @Query("Guarantee") String Guarantee,
                          @Query("Os") String Os,
                          @Query("Processor") String Processor,
                          @Query("Dem") String Dem,
                          @Query("Sd") String Sd,
                          @Query("Battery") String Battery,
                          @Query("Camera") String Camera,
                          @Query("Wight") String Wight,
                          @Query("Sim") String Sim,
                          @Query("Cat") String Cat,
                          @Query("Image") String Image,
                          @Query("Description") String Description
    );


}
