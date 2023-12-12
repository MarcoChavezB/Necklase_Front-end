package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.FirstdInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class FirstdManagment {
    private FirstdInterface interf;

    public FirstdManagment(Retrofit retrofit){
        this.interf = retrofit.create(FirstdInterface.class);
    }

    public void getFirstId(String idUser, Callback<FirstdModel> call){
        Call<FirstdModel> model = interf.getDevice(idUser);
        model.enqueue(call);
    }
}
