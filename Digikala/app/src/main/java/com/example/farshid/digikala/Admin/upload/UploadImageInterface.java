package com.example.farshid.digikala.Admin.upload;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface UploadImageInterface {
    @Multipart
    @POST("Server.php")
    Call<UploadObject> uploadFile(@Part MultipartBody.Part file, @Part("name") RequestBody name);
    @Multipart
    @POST("insert_image_to_gallery.php")
    Call<UploadObject> uploadFile2(@Part MultipartBody.Part file, @Part("name") RequestBody name);
    @GET("Server2.php")
    Call<String> Update(@Query("Id") String Id ,@Query("upload_paths") String upload_paths);
    @GET("insert_image_to_gallery2.php")
    Call<String> Update2(@Query("Id") String Id ,@Query("upload_paths") String upload_paths);

}