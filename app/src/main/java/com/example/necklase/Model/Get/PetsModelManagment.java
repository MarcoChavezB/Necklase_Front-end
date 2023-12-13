package com.example.necklase.Model.Get;

import com.example.necklase.Model.Post.RegisterPetOnlyModel;
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
    public void deletePet(String id, Callback<MessageModel> call){
        Call<MessageModel> del = inter.deletepet(id);
        del.enqueue(call);
    }
    public void getDevicePet(String id, Callback<DevicePetModel> call){
        Call<DevicePetModel> del = inter.getdevicePet(id);
        del.enqueue(call);
    }

    public void updatepetdevice(String deviceid, String petid, Callback<MessageModel> call){
        Call<MessageModel> del = inter.updatedevicepet(petid, deviceid);
        del.enqueue(call);
    }

    public void registerPet (String nombre, String id, String raza, String genero, Callback<MessageModel> call){
        RegisterPetOnlyModel pts = new RegisterPetOnlyModel(id, nombre, raza, genero);
        Call<MessageModel> del = inter.registerPet(pts);
        del.enqueue(call);
    }

    public void getPetsWithoutDevice (String iduser,  Callback<List<PetsWithoutDeviceSyncModel>> call){
        Call<List<PetsWithoutDeviceSyncModel>> del = inter.getpetswithout(iduser);
        del.enqueue(call);
    }
}
