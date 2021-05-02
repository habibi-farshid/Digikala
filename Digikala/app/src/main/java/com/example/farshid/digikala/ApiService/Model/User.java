package com.example.farshid.digikala.ApiService.Model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    private String Id;
    @SerializedName("Password")
    private String Password;
    @SerializedName("Email")
    private String Email;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

}
