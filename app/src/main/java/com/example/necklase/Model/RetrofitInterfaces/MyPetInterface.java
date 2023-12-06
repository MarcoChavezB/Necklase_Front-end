package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.Post.PersonalDataPostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyPetInterface {
    @POST("infoMascota/{id}")
    Call<MyPetPostModel> perro(@Path("id") String id, @Body MyPetPostModel myPetPostModel);
}
