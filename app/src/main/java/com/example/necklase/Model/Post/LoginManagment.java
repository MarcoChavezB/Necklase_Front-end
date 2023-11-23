package com.example.necklase.Model.Post;
import com.example.necklase.Model.RetrofitInterfaces.LoginInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginManagment {
    private LoginInterface loginInterface;

    public LoginManagment(Retrofit loginInterface){
        this.loginInterface = loginInterface.create(LoginInterface.class);
    }

    public void postData(String email, String password, Callback<LoginPostModel> callback){
        LoginPostModel loginPostModel = new LoginPostModel(email, password);
        Call<LoginPostModel> call = loginInterface.login(loginPostModel);
        call.enqueue(callback);
    }
}
