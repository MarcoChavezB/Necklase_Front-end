package com.example.necklase.MVVM.Interactors;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.tv.interactive.TvInteractiveAppService;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.necklase.Extras.NotificationHelper;
import com.example.necklase.Model.Get.FirstdManagment;
import com.example.necklase.Model.Get.FirstdModel;
import com.example.necklase.Model.Get.ForeManagment;
import com.example.necklase.Model.Get.ForeModel;
import com.example.necklase.Model.Get.HumManagment;
import com.example.necklase.Model.Get.HumModel;
import com.example.necklase.Model.Get.TempManagment;
import com.example.necklase.Model.Get.TempModel;
import com.example.necklase.Model.Get.caloriasManagment;
import com.example.necklase.Model.Get.caloriasModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

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

    public void setCorrectDevice(String userId){
        FirstdManagment firstdManagment = new FirstdManagment(retrofit);
        firstdManagment.getFirstId(userId, new Callback<FirstdModel>() {
            @Override
            public void onResponse(Call<FirstdModel> call, Response<FirstdModel> response) {
                if(!response.isSuccessful()){
                    return;
                }
                SharedPreferences.Editor deviceInfo = context.getSharedPreferences("deviceInfo", MODE_PRIVATE).edit();
                deviceInfo.putString("deviceId", response.body().getIdDevice());
                deviceInfo.putString("deviceCode", response.body().getCodigo());
                deviceInfo.putString("petId", response.body().getDogId());
                deviceInfo.apply();

                AnaliticsInteractor interactorAnalitics = new AnaliticsInteractor(context);
                interactorAnalitics.getInfoDog(response.body().getIdDevice());

            }

            @Override
            public void onFailure(Call<FirstdModel> call, Throwable t) {}
        });
    }



    private MutableLiveData<String> dogLiveData = new MutableLiveData<>();

    public LiveData<String> getInfoDog(String petId) {
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);
       myPetManagment.getData(petId, new Callback<MyPetPostModel>() {
           @Override
           public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
               if (response.isSuccessful()){
                   String nameDog = response.body().getNombre();
                   dogLiveData.setValue(nameDog);
               }else {}
           }

           @Override
           public void onFailure(Call<MyPetPostModel> call, Throwable t) {}
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
                if(!response.isSuccessful()){
                    return;
                }

                List<String> tempList = new ArrayList<>();

                if (response.body() != null) {
                    String bmrValue = response.body().getBmr() != null ? response.body().getBmr() : "0";
                    tempList.add(bmrValue);

                    String activeCalories = response.body().getActiveCalories() != null ? response.body().getActiveCalories() : "0";
                    tempList.add(activeCalories);

                    String totalCalories = response.body().getTotalCalories() != null ? response.body().getTotalCalories() : "0";
                    tempList.add(totalCalories);
                } else {
                    tempList.add("0");
                    tempList.add("0");
                    tempList.add("0");
                }

                caloriesLiveData.setValue(tempList);
            }

            @Override
            public void onFailure(Call<caloriasModel> call, Throwable t) {

            }
        });
        return caloriesLiveData;
    }


    private MutableLiveData<List<String>> climaLiveData = new MutableLiveData<>();
    public LiveData<List<String>> getClima(String cordenadas){
        ForeManagment fore = new ForeManagment(retrofit);
        fore.getData(cordenadas, new Callback<ForeModel>() {
            @Override
            public void onResponse(Call<ForeModel> call, Response<ForeModel> response) {
             if(!response.isSuccessful()){
                 return;
             }
                List<String> tempList = new ArrayList<>();
                tempList.add(response.body().getCitiLocation());
                tempList.add(response.body().getCitiRegion());
                tempList.add(response.body().getFeelsLike());
                tempList.add(response.body().getTemp());
                tempList.add(response.body().getMintemp());
                tempList.add(response.body().getMaxtemp());
                tempList.add(response.body().getDailyChanceOfRain());

                String temp = response.body().getTemp();
                double tempDouble = Double.parseDouble(temp);
                if(tempDouble > 30){
                    NotificationHelper.showNotification(context, "Temperatura alta", "La temperatura podria no ser ideal para un canino");
                }

                if(tempDouble > 15 && tempDouble < 25){
                    NotificationHelper.showNotification(context, "Temperatura Ideal", "Temperatura optima para pasear a tu canino");
                }

                if(tempDouble < 10){
                    NotificationHelper.showNotification(context, "Temperatura baja", "La temperatura podria no ser ideal para un canino");
                }

                climaLiveData.setValue(tempList);
            }
            @Override
            public void onFailure(Call<ForeModel> call, Throwable t) {

            }
        });
        return climaLiveData;
    }
}
