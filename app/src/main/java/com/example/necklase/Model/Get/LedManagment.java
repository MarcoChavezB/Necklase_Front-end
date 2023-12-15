package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.LedInterfaz;
import com.example.necklase.Model.RetrofitInterfaces.LocationInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LedManagment {
    private LedInterfaz interfaz;

    public LedManagment(Retrofit retro){
        interfaz = retro.create(LedInterfaz.class);
    }
    public void setLed(String string, Callback<LedModel> call){
        Call<LedModel> local = interfaz.turnled(string);
        local.enqueue(call);
    }

    public void setbuzzer(String string, Callback<LedModel> call){
        Call<LedModel> local = interfaz.turnbuzzer(string);
        local.enqueue(call);
    }
}
