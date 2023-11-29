package com.example.necklase.Model.Post;

import com.example.necklase.Model.RetrofitInterfaces.ChechDevicesInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CheckDevicesManagment {
    private ChechDevicesInterface chechDevicesInterface;

    public CheckDevicesManagment(Retrofit retrofit){
     this.chechDevicesInterface = retrofit.create(ChechDevicesInterface.class);
    }

    public void getData(String id, Callback<CheckDevicesPostModel> callback){
        CheckDevicesPostModel checkDevices = new CheckDevicesPostModel(id);
        Call<CheckDevicesPostModel> call = chechDevicesInterface.devices(id, checkDevices);
        call.enqueue(callback);
    }

}
