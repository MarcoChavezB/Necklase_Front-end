package com.example.necklase.Model.Post;

import com.example.necklase.Model.RetrofitInterfaces.RegisterInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterManagment {
    private RegisterInterface registerInterface;

    public RegisterManagment(Retrofit registerInterface){
        this.registerInterface = registerInterface.create(RegisterInterface.class);
    }

    public void postData(String name, String lastName, String email, String password, Callback<RegisterPostModel> callback){
        RegisterPostModel registerPostModel = new RegisterPostModel(name, lastName, email, password);
        Call<RegisterPostModel> call = registerInterface.register(registerPostModel);
        call.enqueue(callback);
    }
}
