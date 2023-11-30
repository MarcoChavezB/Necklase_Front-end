package com.example.necklase.MVVM.Interactors;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.necklase.Model.Get.DeviceUserManagment;
import com.example.necklase.Model.Get.DeviceUserModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.ViewModel.SelectDogViewModel;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectDogInteractors {
    private Context context;

    public SelectDogInteractors(Context context){
        this.context = context;}

    public void getDeviceUser(String id, SelectDogViewModel viewModel) {

        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        DeviceUserManagment deviceUserManagment = new DeviceUserManagment(retrofit);

        deviceUserManagment.getData(id, new Callback<List<DeviceUserModel>>() {
            @Override
            public void onResponse(Call<List<DeviceUserModel>> call, Response<List<DeviceUserModel>> response) {
                int responseCode = response.code();
                if (response.isSuccessful()) {

                    Toast.makeText(context, "Correcto", Toast.LENGTH_SHORT).show();
                    List<DeviceUserModel> deviceUserList = response.body();
                    viewModel.onDataReceived(deviceUserList);
                } else {
                    Toast.makeText(context, "Error de servidor"  , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DeviceUserModel>> call, Throwable t) {
                Toast.makeText(context, "Error de servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
