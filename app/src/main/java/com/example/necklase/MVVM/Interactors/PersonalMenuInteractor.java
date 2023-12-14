package com.example.necklase.MVVM.Interactors;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.LogoutManagment;
import com.example.necklase.Model.Post.LogoutPostModel;
import com.example.necklase.Model.Post.PersonalDataManagment;
import com.example.necklase.Model.Post.PersonalDataPostModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.View.login_view;

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


    public void logout(String userId){

        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        LogoutManagment logoutManagment = new LogoutManagment(retrofit);

        logoutManagment.postData(userId, new Callback<LogoutPostModel>() {
            @Override
            public void onResponse(Call<LogoutPostModel> call, Response<LogoutPostModel> response) {
                if (response.isSuccessful()){
                    SharedPreferences.Editor editor = context.getSharedPreferences("loginPrefs", context.MODE_PRIVATE).edit();
                    editor.remove("token");
                    editor.apply();

                    SharedPreferences.Editor editorRM = context.getSharedPreferences("deviceID", context.MODE_PRIVATE).edit();
                    editorRM.remove("id");
                    editorRM.apply();

                    SharedPreferences.Editor editor2 = context.getSharedPreferences("DogInfo", context.MODE_PRIVATE).edit();
                    editor2.remove("nombre");
                    editor2.remove("raza");
                    editor2.remove("genero");
                    editor2.apply();

                    SharedPreferences.Editor removeCode = context.getSharedPreferences("code", context.MODE_PRIVATE).edit();
                    removeCode.remove("code");
                    removeCode.apply();

                    SharedPreferences.Editor removeCodeCollar = context.getSharedPreferences("collar", context.MODE_PRIVATE).edit();
                    removeCodeCollar.remove("codigo");
                    removeCodeCollar.apply();

                    Intent intent = new Intent(context, login_view.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }else{

                }
            }
            @Override
            public void onFailure(Call<LogoutPostModel> call, Throwable t) {}
        });
    }

}
