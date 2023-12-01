package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.FirstCollarInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class FirstCollarManagment {
    private FirstCollarInterface firstCollarInterface;

    public FirstCollarManagment(Retrofit retrofit){
     this.firstCollarInterface = retrofit.create(FirstCollarInterface.class);
    }

    public void getData(String id, Callback<FirstCollarModel> callback){
        FirstCollarModel firstCollar = new FirstCollarModel(id);
        Call<FirstCollarModel> call = firstCollarInterface.getData(firstCollar, id);
        call.enqueue(callback);
    }
}
