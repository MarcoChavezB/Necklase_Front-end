package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.necklase.Model.Get.LedManagment;
import com.example.necklase.Model.Get.LedModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.View.activity_pets_info;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LedInteractor {
    Context context;

    public LedInteractor(Context context) {
        this.context = context;
    }

    public LedModel setled(String valor){
        RetrofitApiModelToken rk = new RetrofitApiModelToken();
        Retrofit rt = rk.provideRetrofit();
        LedManagment led = new LedManagment(rt);

        led.setLed(valor, new Callback<LedModel>() {
            @Override
            public void onResponse(Call<LedModel> call, Response<LedModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Request error", Toast.LENGTH_SHORT).show();
                }  else if (response.code() == 200) {
                    Toast.makeText(context, "Led and buzzer succesfully changed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LedModel> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    public LedModel setBuzzer(String valor){
        RetrofitApiModelToken rk = new RetrofitApiModelToken();
        Retrofit rt = rk.provideRetrofit();
        LedManagment led = new LedManagment(rt);

        led.setbuzzer(valor, new Callback<LedModel>() {
            @Override
            public void onResponse(Call<LedModel> call, Response<LedModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Request error", Toast.LENGTH_SHORT).show();
                }  else if (response.code() == 200) {
                    Toast.makeText(context, "Buzzer succesfully changed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LedModel> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}
