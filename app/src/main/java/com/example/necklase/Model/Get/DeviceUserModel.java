package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class DeviceUserModel {
    @SerializedName("id")
    private String id;

    @SerializedName("modelo")
    private String modelo;

    @SerializedName("codigo")
    private String codigo;

    public DeviceUserModel(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public String getModelo(){
        return modelo;
    }

    public String getCodigo(){
        return codigo;
    }
}
