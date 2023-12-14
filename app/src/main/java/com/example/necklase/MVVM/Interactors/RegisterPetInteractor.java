package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.Get.PetsModelManagment;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.RegisterPetManagment;
import com.example.necklase.View.activity_PetWeight;
import com.example.necklase.View.activity_register_pet;
import com.example.necklase.View.activity_tutorial;
import com.example.necklase.View.navbar;

import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterPetInteractor {
    private Context context;
    public RegisterPetInteractor(Context context){
        this.context = context;
    }
    public void RegistrarPet(String nombre, String raza, String genero, String user_id, String code){
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit instancia = retro.provideRetrofit();
        RegisterPetManagment regispet = new RegisterPetManagment(instancia);
        regispet.postData(nombre, raza, genero, user_id, code, new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.code() == 404){
                    Toast.makeText(context, "Request Error", Toast.LENGTH_SHORT).show();
                } else if (response.body() != null){
                    if (Objects.equals(response.body().getMsg(), "Mascota registrada") && response.code() == 201) {
                        Intent intent = new Intent(context, activity_tutorial.class);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Null Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void RegisterPetOnly(String nombre, String raza, String genero, String user_id){
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit instancia = retro.provideRetrofit();
        PetsModelManagment regispet = new PetsModelManagment(instancia);

        regispet.registerPet(nombre, user_id, raza, genero, new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.code() == 404){
                    Toast.makeText(context, "Request Error", Toast.LENGTH_SHORT).show();
                } else if (response.body() != null){
                    if (Objects.equals(response.body().getMsg(), "Mascota registrada") && response.code() == 201) {
                        Intent intent = new Intent(context, navbar.class);
                        Toast.makeText(context, "Pet successfully registered", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "Null Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
