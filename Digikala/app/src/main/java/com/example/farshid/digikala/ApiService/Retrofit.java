package com.example.farshid.digikala.ApiService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.example.farshid.digikala.ApiService.Interface.userinterface;
import com.example.farshid.digikala.ApiService.Model.User;
import com.example.farshid.digikala.ApiService.Model.user_responce;
import com.example.farshid.digikala.R;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        userinterface userinterface = ApiClient.getClient().create(com.example.farshid.digikala.ApiService.Interface.userinterface.class);
        retrofit2.Call<user_responce> call = userinterface.getUsers();
        call.enqueue(new Callback<user_responce>() {
            @Override
            public void onResponse(retrofit2.Call<user_responce> call, Response<user_responce> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body().getUsers();
                    for (User user:users){
                        Log.i( "Email ",user.getEmail());
                        Log.i( "ID ",user.getId());
                        Log.i( "getPassword ",user.getPassword());
                    }


                }
            }

            @Override
            public void onFailure(retrofit2.Call<user_responce> call, Throwable t) {

            }
        });
    }
}
