package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.RegisterPetManagment;
import com.example.necklase.View.activity_PetWeight;
import com.example.necklase.View.activity_register_pet;
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
                    Intent intent = new Intent(context, activity_register_pet.class);
                    context.startActivity(intent);
                } else if (response.body() != null){
                    if (Objects.equals(response.body().getMsg(), "Mascota registrada") && response.code() == 201) {
                        Intent intent = new Intent(context, activity_PetWeight.class);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, activity_register_pet.class);
                        context.startActivity(intent);
                        Toast.makeText(context, "Null Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Intent intent = new Intent(context, activity_register_pet.class);
                context.startActivity(intent);
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
