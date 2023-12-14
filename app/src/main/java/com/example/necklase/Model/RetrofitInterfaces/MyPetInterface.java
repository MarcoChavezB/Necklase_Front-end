package com.example.necklase.Model.RetrofitInterfaces;

import com.example.necklase.Model.Get.DevicePetModel;
import com.example.necklase.Model.Get.MessageModel;
import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.Model.Get.PetsWithoutDeviceSyncModel;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.Post.PersonalDataPostModel;
import com.example.necklase.Model.Post.RegisterPetOnlyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyPetInterface {
    @POST("infoMascota/{id}")
    Call<MyPetPostModel> perro(@Path("id") String id, @Body MyPetPostModel myPetPostModel);

    @POST("perrosxUsuario/{id}")
    Call<List<PetModel>> getMascotas(@Path("id") String id);

    @DELETE("deletePet/{id}")
    Call<MessageModel> deletepet(@Path("id") String id);

    @POST("infoDispositivo/{id}")
    Call<DevicePetModel> getdevicePet(@Path("id") String id);

    @PUT("updateDevicePet/{PetId}/{DeviceId}")
    Call<MessageModel> updatedevicepet(@Path("PetId") String PetId, @Path("DeviceId") String DeviceId);

    @POST("registerPet")
    Call<MessageModel> registerPet(@Body RegisterPetOnlyModel petmodel);
    @GET("getPetsWithoutDevice/{iduser}")
    Call<List<PetsWithoutDeviceSyncModel>> getpetswithout(@Path("iduser") String iduser);

    @POST("linkPetDisp/{PetId}/{DeviceId}")
    Call<MessageModel> registerpetonly(@Path("PetId") String PetId, @Path("DeviceId") String DeviceId);
}
