package com.example.necklase.MVVM.Interactors;
import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.necklase.Extras.NotificationHelper;
import com.example.necklase.Model.Get.AirManagment;
import com.example.necklase.Model.Get.AirModel;
import com.example.necklase.Model.Get.HumManagment;
import com.example.necklase.Model.Get.HumModel;
import com.example.necklase.Model.Get.TempManagment;
import com.example.necklase.Model.Get.TempModel;
import com.example.necklase.Model.Get.emocionalManagment;
import com.example.necklase.Model.Get.emocionalModel;
import com.example.necklase.Model.Get.tempPerHourManagment;
import com.example.necklase.Model.Get.tempPerHourModel;
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

    private final MutableLiveData<String> infoLiveData = new MutableLiveData<>();

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
                } else {}
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
                } else {}
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
                if(response.isSuccessful()){
                    String level = String.valueOf(response.body().getNivel());
                    infoAir.setValue(level);
                }else {}
            }

            @Override
            public void onFailure(Call<AirModel> call, Throwable t) {

            }
        });
        return infoAir;
    }



    // error en la peticion pues no tiene datos
    private MutableLiveData<List<String>> infoEmocional = new MutableLiveData<>();
    public LiveData<List<String>> getEmocion(String deviceCode) {
        emocionalManagment emocion = new emocionalManagment(retrofit);
        emocion.getData(deviceCode, new Callback<emocionalModel>() {
            @Override
            public void onResponse(Call<emocionalModel> call, Response<emocionalModel> response) {
                if(!response.isSuccessful()){
                    return;
                }

                List<String> tempList = new ArrayList<>();

                tempList.add(response.body().getRestingTime());
                tempList.add(response.body().nivelFelicidad());

                infoEmocional.setValue(tempList);
            }

            @Override
            public void onFailure(Call<emocionalModel> call, Throwable t) {

            }
        });
        return infoEmocional;
    }

    private MutableLiveData<List<tempPerHourModel>> infoGraph = new MutableLiveData<>();
    public LiveData<List<tempPerHourModel>> getGraph(String deviceCode) {
        tempPerHourManagment tempGraph = new tempPerHourManagment(retrofit);
        tempGraph.getGraph(deviceCode, new Callback<List<tempPerHourModel>>() {
            @Override
            public void onResponse(Call<List<tempPerHourModel>> call, Response<List<tempPerHourModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error en peticion grafica", Toast.LENGTH_SHORT).show();
                    return;
                }


                infoGraph.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<tempPerHourModel>> call, Throwable t) {
                NotificationHelper.showNotification(context, "error", t.getCause().toString());
            }
        });
        return infoGraph;
    }


}
