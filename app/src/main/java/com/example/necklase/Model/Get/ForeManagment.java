package com.example.necklase.Model.Get;
import com.example.necklase.Model.RetrofitInterfaces.ForeInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ForeManagment {
    private final ForeInterface foreInterface;

    public ForeManagment(Retrofit retro){
        this.foreInterface = retro.create(ForeInterface.class);
    }

    public void getData(String coords, Callback<ForeModel> callback){
        Call<ForeModel> call = foreInterface.getData(coords);
        call.enqueue(callback);
    }
}
