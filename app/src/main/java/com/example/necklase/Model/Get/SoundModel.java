package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class SoundModel {
    @SerializedName("value")
    private String value;

    private String device;

    public SoundModel(String device){
        this.device = device;
    }

    public String SoundModel(String device){
        return value;
    }
}
