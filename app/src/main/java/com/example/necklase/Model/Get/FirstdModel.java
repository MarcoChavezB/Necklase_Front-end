package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class FirstdModel {
    @SerializedName("device_id")
    private String idDevice;

    @SerializedName("pet_id")
    private String dogId;

    @SerializedName("codigo")
    private String codigo;

    public String getIdDevice() {
        return idDevice;
    }
    public String getDogId() {
        return dogId;
    }
    public String getCodigo(){
        return codigo;
    }
}
