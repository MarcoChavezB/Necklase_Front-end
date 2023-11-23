package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Post.PersonalDataManagment;
import com.example.necklase.Model.Post.PersonalDataPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PersonalDataInterface {
    @POST("InfoUsuario/{id}")
    Call<PersonalDataPostModel> personal(@Path("id") String id, @Body PersonalDataPostModel personalDataPostModel);
}
