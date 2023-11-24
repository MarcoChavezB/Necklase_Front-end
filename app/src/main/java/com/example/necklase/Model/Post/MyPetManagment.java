package com.example.necklase.Model.Post;

import com.example.necklase.Model.RetrofitInterfaces.MyPetInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MyPetManagment {
    private MyPetInterface myPetInterface;

    public MyPetManagment(Retrofit retrofit){
     this.myPetInterface = retrofit.create(MyPetInterface.class);
    }

    public void getData(String id, Callback<MyPetPostModel> callback){
        MyPetPostModel myPet = new MyPetPostModel(id);
        Call<MyPetPostModel> call = myPetInterface.perro(id, myPet);
        call.enqueue(callback);
    }
}
