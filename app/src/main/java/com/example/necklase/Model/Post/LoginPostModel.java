package com.example.necklase.Model.Post;
import com.google.gson.annotations.SerializedName;


public class LoginPostModel {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("msg")
    private String msg;

    @SerializedName("access_token")
    private String access_token;

    public LoginPostModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return access_token;
    }

    public String setToken(String token) {
        this.access_token = token;
        return token;
    }
}
