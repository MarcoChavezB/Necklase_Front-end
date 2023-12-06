package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class HumModel {
    @SerializedName("value")
    private int value;

    @SerializedName("deviceCode")
    private String deviceCode;

    private String device;

    public HumModel(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public int getHum(){
        return value;
    }
}
