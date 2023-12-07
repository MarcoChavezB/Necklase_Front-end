package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.TempInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class TempManagment {
    private final TempInterface tempInterface;

    public TempManagment(Retrofit retro){
        this.tempInterface = retro.create(TempInterface.class);
    }

    public void getData(String device, Callback<TempModel> callback){
        Call<TempModel> temp = tempInterface.getData(device);
        temp.enqueue(callback);
    }
}
