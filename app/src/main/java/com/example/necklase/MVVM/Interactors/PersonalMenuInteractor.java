package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.PersonalDataManagment;
import com.example.necklase.Model.Post.PersonalDataPostModel;
import com.example.necklase.Model.Token.JwtUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonalMenuInteractor {
    private Context context;
    public PersonalMenuInteractor(Context context){
           this.context = context;
    }

    RetrofitApiModelToken retrofitApiModel = new RetrofitApiModelToken();
    Retrofit retrofit = retrofitApiModel.provideRetrofit();




    private MutableLiveData<List<String>> infoLiveData = new MutableLiveData<>();

    public MutableLiveData<List<String>> getInfoData(String id){
        PersonalDataManagment data = new PersonalDataManagment(retrofit);
        data.getData(id, new Callback<PersonalDataPostModel>() {
            @Override
            public void onResponse(Call<PersonalDataPostModel> call, Response<PersonalDataPostModel> response) {
                if(!response.isSuccessful()){
                    return;
                }
                String name = response.body().getNombre();
                String email = response.body().getEmail();

                List<String> tempList = infoLiveData.getValue();
                if (tempList == null) {
                    tempList = new ArrayList<>();
                }
                tempList.add(name);
                tempList.add(email);
                infoLiveData.setValue(tempList);
            }

            @Override
            public void onFailure(Call<PersonalDataPostModel> call, Throwable t) {

            }
        });
        return infoLiveData;
    }

}
