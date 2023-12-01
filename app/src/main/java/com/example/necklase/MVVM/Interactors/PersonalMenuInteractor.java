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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonalMenuInteractor {
    private Context context;
    public String namePerson;
    public String emailPerson;


    public PersonalMenuInteractor(Context context){
           this.context = context;
    }

    public void getInfoData(String id){

        RetrofitApiModelToken retrofitApiModel = new RetrofitApiModelToken();
        Retrofit retrofit = retrofitApiModel.provideRetrofit();
        PersonalDataManagment personalDataManagment = new PersonalDataManagment(retrofit);

        personalDataManagment.getData(id, new Callback<PersonalDataPostModel>() {
            @Override
            public void onResponse(Call<PersonalDataPostModel> call, Response<PersonalDataPostModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PersonalDataPostModel personalData = response.body();
                    namePerson = personalData.getNombre();
                    emailPerson = personalData.getEmail();

                    SharedPreferences.Editor editor = context.getSharedPreferences("PersonalInfo", context.MODE_PRIVATE).edit();
                    editor.putString("name", namePerson);
                    editor.putString("email", emailPerson);
                    editor.apply();
                }
            }

            @Override
            public void onFailure(Call<PersonalDataPostModel> call, Throwable t){}
        });
    }

    public String getNamePerson() {
        return namePerson;
    }
    public String getEmailPerson() {
        return emailPerson;
    }
}
