package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.tempPerHourInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class tempPerHourManagment {
    private tempPerHourInterface tempInterface;

    public tempPerHourManagment(Retrofit retrofit){
        tempInterface = retrofit.create(tempPerHourInterface.class);
    }

    public void getGraph(String deviceCode, retrofit2.Callback<List<tempPerHourModel>> callback){
        Call<List<tempPerHourModel>> model = tempInterface.getGraph(deviceCode);
        model.enqueue(callback);
    }
}
