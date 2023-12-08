package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.HumManagment;
import com.example.necklase.Model.Get.HumModel;
import com.example.necklase.Model.Get.TempManagment;
import com.example.necklase.Model.Get.TempModel;
import com.example.necklase.Model.Get.caloriasManagment;
import com.example.necklase.Model.Get.caloriasModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeInteractor {
    Context context;

    public HomeInteractor(Context context) {
        this.context = context;
    }


    RetrofitApiModelToken retro = new RetrofitApiModelToken();
    Retrofit retrofit = retro.provideRetrofit();

    private MutableLiveData<String> humLiveData = new MutableLiveData<>();

    public LiveData<String> getHum(String deviceCode) {
        HumManagment humManagment = new HumManagment(retrofit);
        humManagment.getData(deviceCode, new Callback<HumModel>() {
            @Override
            public void onResponse(Call<HumModel> call, Response<HumModel> response) {
                int responseCode = response.code();
                if (response.isSuccessful()) {
                    String hum = String.valueOf(response.body().getHum());
                    humLiveData.setValue(hum);
                } else {
                }
            }
            @Override
            public void onFailure(Call<HumModel> call, Throwable t) {

            }
        });

        return humLiveData;
    }

    private MutableLiveData<String> dogLiveData = new MutableLiveData<>();

    public LiveData<String> getInfoDog(String id) {
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);
       myPetManagment.getData(id, new Callback<MyPetPostModel>() {
           @Override
           public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
               if (response.isSuccessful()){
                   String nameDog = response.body().getNombre();
                   SharedPreferences.Editor editor = context.getSharedPreferences("DogInfo", context.MODE_PRIVATE).edit();
                   editor.putString("nombre", response.body().getNombre());
                   editor.putString("raza", response.body().getRaza());
                   editor.putString("genero", response.body().getGenero());
                   editor.apply();
                   dogLiveData.setValue(nameDog);
               }else{}
           }

           @Override
           public void onFailure(Call<MyPetPostModel> call, Throwable t) {

           }
       });
        return dogLiveData;
    }

    private MutableLiveData<String> tempLiveData = new MutableLiveData<>();

    public LiveData<String>  getTemp(String device){
        TempManagment tempManagment = new TempManagment(retrofit);
        tempManagment.getData(device, new Callback<TempModel>() {
            @Override
            public void onResponse(Call<TempModel> call, Response<TempModel> response) {
                if(response.isSuccessful()){
                    tempLiveData.setValue(String.valueOf(response.body().getTemp()));
                }
            }

            @Override
            public void onFailure(Call<TempModel> call, Throwable t) {

            }
        });
        return tempLiveData;
    }

    private MutableLiveData<List<String>> caloriesLiveData = new MutableLiveData<>();

    public LiveData<List<String>> getCalories(String device, String peso){
        caloriasManagment calories = new caloriasManagment(retrofit);
        calories.getData(device, peso, new Callback<caloriasModel>() {
            @Override
            public void onResponse(Call<caloriasModel> call, Response<caloriasModel> response) {
                int codeResponse = response.code();
                if(!response.isSuccessful()){
                    return;
                }

                List<String> tempList = new ArrayList<>();

                tempList.add(response.body().getBmr());
                tempList.add(response.body().getActiveCalories());
                tempList.add(response.body().getTotalCalories());

                caloriesLiveData.setValue(tempList);
            }

            @Override
            public void onFailure(Call<caloriasModel> call, Throwable t) {

            }
        });
        return caloriesLiveData;
    }

}
