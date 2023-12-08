package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class emocionalModel {
    @SerializedName("deviceCode")
    private String deviceCode;

    @SerializedName("restTime")
    private String restTime;

    @SerializedName("happinessLevel")
    private String happinessLevel;

    public emocionalModel(String deviceCode){
        this.deviceCode = deviceCode;
    }

    public String getRestingTime(){
        return restTime;
    }
    public String nivelFelicidad(){
        return happinessLevel;
    }
}
