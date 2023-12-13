package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.SoundInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SoundManagment {
    private SoundInterface inter;
    public SoundManagment(Retrofit retrofit){
        this.inter = retrofit.create(SoundInterface.class);
    }
    public void getData(String device, Callback<SoundModel> call){
        Call<SoundModel> model = inter.getSound(device);
        model.enqueue(call);
    }
}
