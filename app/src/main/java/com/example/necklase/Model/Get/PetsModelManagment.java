package com.example.necklase.Model.Get;

import com.example.necklase.Model.RetrofitInterfaces.MyPetInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PetsModelManagment {

    private MyPetInterface inter;
    public PetsModelManagment(Retrofit retrofit){
        inter = retrofit.create(MyPetInterface.class);
    }
    public void getData(String id, Callback<List<PetModel>> call){
        Call<List<PetModel>> pet = inter.getMascotas(id);
        pet.enqueue(call);
    }
}
