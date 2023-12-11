package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class tempPerHourModel {
    @SerializedName("value")
    private String values;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("deviceCode")
    private String deviceCode;

    public tempPerHourModel(String deviceCode){
        this.deviceCode = deviceCode;
    }

    public String getValue(){
        return values;
    }

    public String getCreated_at(){
        return created_at;
    }
}
