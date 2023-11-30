package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.DevicesUserInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DeviceUserManagment {
    private DevicesUserInterface devicesUserInterface;

    public DeviceUserManagment(Retrofit retrofit){
        this.devicesUserInterface = retrofit.create(DevicesUserInterface.class);
    }

    public void getData(String id, Callback<List<DeviceUserModel>> callback){
        DeviceUserModel deviceUserModel = new DeviceUserModel(id);
        Call<List<DeviceUserModel>> call = devicesUserInterface.getDevices(deviceUserModel);
        call.enqueue(callback);
    }
}
