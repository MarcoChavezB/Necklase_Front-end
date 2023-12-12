package com.example.necklase.Model.Post;

import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.RetrofitInterfaces.RegisterPetInterfaz;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RegisterPetManagment {

    private RegisterPetInterfaz registerInterface;

    public RegisterPetManagment(Retrofit registerInterface){
        this.registerInterface = registerInterface.create(RegisterPetInterfaz.class);
    }

    public void postData(String nombre, String raza, String genero, String user_id, String codigo, Callback<MessageModel> callback){
        RegisterPetModel registerPostModel = new RegisterPetModel(codigo,nombre,raza,genero,user_id);
        Call<MessageModel> call = registerInterface.register(registerPostModel);
        call.enqueue(callback);
    }
}
