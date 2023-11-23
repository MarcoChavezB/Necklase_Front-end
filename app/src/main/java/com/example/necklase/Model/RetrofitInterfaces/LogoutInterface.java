package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Post.LogoutPostModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LogoutInterface {
    @POST("logout")
    Call<LogoutPostModel> logout(@Body LogoutPostModel logoutPostModel);
}
