package com.example.necklase.Model.RetrofitInterfaces;
import com.example.necklase.Model.Post.CheckDevicesPostModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChechDevicesInterface {
    @GET("getcount/{id}")
    Call<CheckDevicesPostModel> devices(@Path("id") String id, @Body CheckDevicesPostModel checkDevicesPostModel);
}
