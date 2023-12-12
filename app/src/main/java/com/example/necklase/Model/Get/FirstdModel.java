package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class FirstdModel {
    @SerializedName("id")
    private String idDevice;

    @SerializedName("pet_id")
    private String dogId;

    public String getDeviceCode() {
        return idDevice;
    }
    public String getDogId() {
        return dogId;
    }
}
