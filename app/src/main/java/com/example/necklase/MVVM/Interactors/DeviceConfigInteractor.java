package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.MVVM.Resource.Resource;
import com.example.necklase.Model.Get.Device;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.RetrofitInterfaces.DispositivosInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceConfigInteractor {
    private RetrofitApiModelToken apiService;

    private String userId;
    public DeviceConfigInteractor(String userId) {
        this.userId = userId;
    }

    public LiveData<Resource<List<Device>>> getMyData(Context getContext) {
        apiService = new RetrofitApiModelToken();
        Call<List<Device>> call = apiService.provideRetrofit().create(DispositivosInterface.class).getDevices(userId);
        MutableLiveData<Resource<List<Device>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));
        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("DeviceCallback", "Data received: " + response.body().toString());
                    liveData.postValue(Resource.success(response.body()));
                } else {
                    Log.e("DeviceCallback", "No data received. Code: " + response.code());
                    liveData.postValue(Resource.error("No hay datos que mostrar", null));
                }
            }
            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Log.e("DeviceCallback", "Call failed with error: " + t.getMessage(), t);
                liveData.postValue(Resource.error("Error al cargar los datos", null));
            }
        });
        return liveData;
    }
}
