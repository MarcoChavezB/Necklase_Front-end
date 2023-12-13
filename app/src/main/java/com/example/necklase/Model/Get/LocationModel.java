package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
    @SerializedName("value")
    private String value;

    @SerializedName("place")
    private String place;

    private String device;

    public LocationModel(String device){
        this.device = device;
    }

    public String getValue() {
        return value;
    }

    public String getPlace() {
        return place;
    }
}
