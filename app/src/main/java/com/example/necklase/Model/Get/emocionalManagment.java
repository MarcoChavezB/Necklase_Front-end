package com.example.necklase.Model.Get;
import com.example.necklase.Model.RetrofitInterfaces.emocionInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class emocionalManagment {
    private final emocionInterface anInterface;

    public emocionalManagment(Retrofit retrofit){
        this.anInterface = retrofit.create(emocionInterface.class);
    }

    public void getData(String device, Callback<emocionalModel> callback){
        Call<emocionalModel> emocion = anInterface.getData(device);
        emocion.enqueue(callback);
    }
}
