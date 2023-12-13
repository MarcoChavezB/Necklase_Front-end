package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.LocationManagment;
import com.example.necklase.Model.Get.LocationModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapsInteractor {

    private Context context;
    private RetrofitApiModelToken retrotoken;

    public MapsInteractor(Context context) {
        this.context = context;
    }

    RetrofitApiModelToken retro = new RetrofitApiModelToken();
    Retrofit retrofit = retro.provideRetrofit();

    private MutableLiveData<List<String>> locationLiveData = new MutableLiveData<>();

    public LiveData<List<String>> getLocation(String deviceCode) {
        LocationManagment manag = new LocationManagment(retrofit);
        manag.getLocation(deviceCode, new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "error code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<String> tempList = new ArrayList<>();

                tempList.add(response.body().getPlace());
                tempList.add(response.body().getValue());
                locationLiveData.setValue(tempList);

                Toast.makeText(context, "code "+ response.code(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {}
        });
        return locationLiveData;
    }

}
