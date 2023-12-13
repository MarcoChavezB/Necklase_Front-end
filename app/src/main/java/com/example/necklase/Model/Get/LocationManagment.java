package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.LocationInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LocationManagment {
    private LocationInterface interfaz;

    public LocationManagment(Retrofit retro){
        interfaz = retro.create(LocationInterface.class);
    }
    public void getLocation(String device, Callback<LocationModel> call){
        Call<LocationModel> local = interfaz.getLocation(device);
        local.enqueue(call);
    }
}
