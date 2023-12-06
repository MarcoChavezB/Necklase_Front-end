package com.example.necklase.MVVM.Interactors;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.tv.interactive.TvInteractiveAppService;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.Model.Get.AirManagment;
import com.example.necklase.Model.Get.AirModel;
import com.example.necklase.Model.Get.HumManagment;
import com.example.necklase.Model.Get.HumModel;
import com.example.necklase.Model.Get.TempManagment;
import com.example.necklase.Model.Get.TempModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnaliticsInteractor {

    Context context;
    public AnaliticsInteractor(Context context){
        this.context = context;
    }

    RetrofitApiModelToken retro = new RetrofitApiModelToken();
    Retrofit retrofit = retro.provideRetrofit();

    private MutableLiveData<String> infoLiveData = new MutableLiveData<>();

    public LiveData<String> getInfoDog(String device) {
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

    private MutableLiveData<List<String>> infoTemData = new MutableLiveData<>();

    public LiveData<List<String>> getTemp(String deviceCode) {
        TempManagment tempManagment = new TempManagment(retrofit);
        tempManagment.getData(deviceCode, new Callback<TempModel>() {
            @Override
            public void onResponse(Call<TempModel> call, Response<TempModel> response) {
                if(response.isSuccessful()){
                    String temp = String.valueOf(response.body().getTemp());
                    String nivel = String.valueOf(response.body().getNivel());
                    List<String> tempList = infoTemData.getValue();
                    if (tempList == null) {
                        tempList = new ArrayList<>();
                    }
                    tempList.add(temp);
                    tempList.add(nivel);
                    infoTemData.setValue(tempList);
                } else {
                    Toast.makeText(context, "Respuesta NO exitosa en temperatura", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TempModel> call, Throwable t) {
            }
        });
        return infoTemData;
    }

    private MutableLiveData<String> infoAir = new MutableLiveData<>();

    public LiveData<String> getLevelAir(String deviceCode){
        AirManagment airManagment = new AirManagment(retrofit);

        airManagment.getData(deviceCode, new Callback<AirModel>() {
            @Override
            public void onResponse(Call<AirModel> call, Response<AirModel> response) {
                int responseCode = response.code();

                if(response.isSuccessful()){
                    String level = String.valueOf(response.body().getNivel());
                    infoAir.setValue(level);
                }else {
                    Toast.makeText(context, "Respuesta NO exitosa"+ responseCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AirModel> call, Throwable t) {

            }
        });
        return infoAir;
    }

}
