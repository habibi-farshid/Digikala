package com.example.farshid.digikala.ApiService.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class user_responce {

    @SerializedName("resp")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
