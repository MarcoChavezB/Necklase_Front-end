package com.example.necklase.Model.Post;

import com.example.necklase.Model.RetrofitInterfaces.NdispositivosInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class NdispositivosManagment {
    private NdispositivosInterface ndispositivos;

    public NdispositivosManagment (Retrofit retrofit){
        this.ndispositivos = retrofit.create(NdispositivosInterface.class);
    }

    public void postData(String id, Callback<NdispositivosModel> callback){
        NdispositivosModel ndispositivosModel = new NdispositivosModel(id);
        Call<NdispositivosModel> call = ndispositivos.dispositivos(id, ndispositivosModel);
        call.enqueue(callback);
    }
}
