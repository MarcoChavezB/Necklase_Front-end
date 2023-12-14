package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.DevicePetModel;
import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.Get.PetsModelManagment;
import com.example.necklase.Model.Get.PetsWithoutDeviceSyncModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.R;
import com.example.necklase.View.MainActivity;
import com.example.necklase.View.activity_Device_pet_info;
import com.example.necklase.View.activity_device;
import com.example.necklase.View.activity_pets_info;
import com.example.necklase.View.navbar;

import java.util.List;

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
    public MutableLiveData<List<PetsWithoutDeviceSyncModel>> pets = new MutableLiveData<>();

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

    public List<PetsWithoutDeviceSyncModel> getDataChange(String iduser){
        RetrofitApiModelToken api = new RetrofitApiModelToken();
        Retrofit miretro = api.provideRetrofit();
        PetsModelManagment manag = new PetsModelManagment(miretro);
        manag.getPetsWithoutDevice(iduser, new Callback<List<PetsWithoutDeviceSyncModel>>() {
            @Override
            public void onResponse(Call<List<PetsWithoutDeviceSyncModel>> call, Response<List<PetsWithoutDeviceSyncModel>> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
                } else if (response.body() != null){
                    pets.postValue(response.body());
                } else{
                    Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<PetsWithoutDeviceSyncModel>> call, Throwable t) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    public MessageModel updateDevicepet(String iddevice, String idpet){
        RetrofitApiModelToken api = new RetrofitApiModelToken();
        Retrofit miretro = api.provideRetrofit();
        PetsModelManagment manag = new PetsModelManagment(miretro);
        manag.updatepetdevice(iddevice, idpet, new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Null Error", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 200){
                    Toast.makeText(context, "Pet changed with successful device", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);

                } else{
                    Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
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
