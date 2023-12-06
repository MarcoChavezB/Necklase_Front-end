package com.example.necklase.MVVM.Interactors;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.HumManagment;
import com.example.necklase.Model.Get.HumModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnaliticsInteractor {

    Context context;
    public AnaliticsInteractor(Context context){
        this.context = context;
    }

    private MutableLiveData<String> infoLiveData = new MutableLiveData<>();

    public LiveData<String> getInfoDog(String device) {
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);

        myPetManagment.getData(device, new Callback<MyPetPostModel>() {
            @Override
            public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
                if (response.isSuccessful()) {
                    String info = response.body().getNombre();
                    String codigoCollar = response.body().getCodigo();

                    SharedPreferences.Editor editor = context.getSharedPreferences("collar", MODE_PRIVATE).edit();
                    editor.putString("codigo", codigoCollar);
                    editor.apply();

                    infoLiveData.setValue(info);
                } else {}
            }

            @Override
            public void onFailure(Call<MyPetPostModel> call, Throwable t) {

            }
        });

        return infoLiveData;
    }

    private MutableLiveData<String> humLiveData = new MutableLiveData<>();

    public LiveData<String> getHum(String deviceCode) {
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        HumManagment humManagment = new HumManagment(retrofit);

        humManagment.getData(deviceCode, new Callback<HumModel>() {
            @Override
            public void onResponse(Call<HumModel> call, Response<HumModel> response) {
                int responseCode = response.code();
                if (response.isSuccessful()) {
                    String hum = String.valueOf(response.body().getHum());
                    humLiveData.setValue(hum);
                    Toast.makeText(context, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Respuesta NO exitosa" + responseCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HumModel> call, Throwable t) {

            }
        });

        return humLiveData;
    }



}
