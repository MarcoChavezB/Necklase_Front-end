package com.example.necklase.Model.Post;

import com.example.necklase.Model.RetrofitInterfaces.LogoutInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LogoutManagment {
    private LogoutInterface logoutInterface;

    public LogoutManagment(Retrofit logoutInterface){
        this.logoutInterface = logoutInterface.create(LogoutInterface.class);
    }

    public void postData(String id, Callback<LogoutPostModel> callback){
        LogoutPostModel logoutPostModel = new LogoutPostModel(id);
        Call<LogoutPostModel> call = logoutInterface.logout(logoutPostModel);
        call.enqueue(callback);
    }
}
