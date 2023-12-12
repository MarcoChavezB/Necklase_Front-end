package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.Model.Get.PetsModelManagment;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.View.register_view;

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
                } else if (response.body() != null){
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
}
