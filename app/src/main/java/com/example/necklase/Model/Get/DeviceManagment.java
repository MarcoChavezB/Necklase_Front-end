package com.example.necklase.Model.Get;
import com.example.necklase.Model.Post.LoginPostModel;
import com.example.necklase.Model.RetrofitInterfaces.DispositivosInterface;
import com.example.necklase.Model.RetrofitInterfaces.LoginInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DeviceManagment {
    private DispositivosInterface  dispositivosInterface;

    public DeviceManagment(Retrofit DispositivosInterface){
        this.dispositivosInterface = DispositivosInterface.create(DispositivosInterface.class);
    }


}
