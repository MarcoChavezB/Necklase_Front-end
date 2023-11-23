package com.example.necklase.Model.Post;

import com.example.necklase.Model.RetrofitInterfaces.PersonalDataInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PersonalDataManagment {
    private PersonalDataInterface personalDataInterface;

    public PersonalDataManagment(Retrofit retrofit){
     this.personalDataInterface = retrofit.create(PersonalDataInterface.class);
    }

    public void getData(String id, Callback<PersonalDataPostModel> callback){
        PersonalDataPostModel personalData = new PersonalDataPostModel(id);
        Call<PersonalDataPostModel> call = personalDataInterface.personal(id, personalData);
        call.enqueue(callback);
    }
}
