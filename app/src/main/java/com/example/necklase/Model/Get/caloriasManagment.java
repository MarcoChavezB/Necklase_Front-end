package com.example.necklase.Model.Get;


import com.example.necklase.Model.RetrofitInterfaces.caloriasInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class caloriasManagment {
    private final caloriasInterface calorias;
    public caloriasManagment(Retrofit retro){
        this.calorias = retro.create(caloriasInterface.class);
    }

    public void getData(String device, String peso, Callback<caloriasModel> callback){
        Call<caloriasModel> call = calorias.getData(device, peso);
        call.enqueue(callback);
    }
}
