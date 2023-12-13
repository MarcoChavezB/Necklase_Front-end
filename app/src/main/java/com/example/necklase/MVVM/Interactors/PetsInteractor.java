package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.Model.Get.PetsModelManagment;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.View.activity_pets_info;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PetsInteractor {
    private Context context;

    public PetsInteractor(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<PetModel>> lista = new MutableLiveData<>();

    public List<PetModel> getPets(String id) {
        RetrofitApiModelToken api = new RetrofitApiModelToken();
        Retrofit instancia = api.provideRetrofit();
        PetsModelManagment mana = new PetsModelManagment(instancia);

        mana.getData(id, new Callback<List<PetModel>>() {
            @Override
            public void onResponse(Call<List<PetModel>> call, Response<List<PetModel>> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (response.body() != null && !response.body().isEmpty()){
                    lista.postValue(response.body());
                } else{
                    Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<PetModel>> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    public MessageModel deletepet(String id) {
        RetrofitApiModelToken api = new RetrofitApiModelToken();
        Retrofit instancia = api.provideRetrofit();
        PetsModelManagment mana = new PetsModelManagment(instancia);

        mana.deletePet(id, new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Pet not found", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 422){
                    Toast.makeText(context, "This pet is synchronized with a device", Toast.LENGTH_LONG).show();
                } else if (response.code() == 201) {
                    Toast.makeText(context, "was successfully removed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, activity_pets_info.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
