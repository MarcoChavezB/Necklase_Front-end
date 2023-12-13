package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.DevicePetModel;
import com.example.necklase.Model.Get.PetsModelManagment;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DevicePetInfoInteractor {
    private Context context;

    public DevicePetInfoInteractor(Context context) {
        this.context = context;
    }

    public MutableLiveData<DevicePetModel> info = new MutableLiveData<>();

    public DevicePetModel getData(String iddevice){
        RetrofitApiModelToken api = new RetrofitApiModelToken();
        Retrofit miretro = api.provideRetrofit();
        PetsModelManagment manag = new PetsModelManagment(miretro);
        manag.getDevicePet(iddevice, new Callback<DevicePetModel>() {
            @Override
            public void onResponse(Call<DevicePetModel> call, Response<DevicePetModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (response.body() != null){
                    info.postValue(response.body());
                } else{
                    Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DevicePetModel> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
