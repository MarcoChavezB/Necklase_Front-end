package com.example.necklase.Model.Get;
import com.google.gson.annotations.SerializedName;

public class TempModel {
    @SerializedName("value")
    private int value;

    @SerializedName("deviceCode")
    private String deviceCode;

    @SerializedName("nivel")
    private String nivel;

    private String device;

    public TempModel(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public int getTemp(){
        return value;
    }

    public String getNivel(){
        return nivel;
    }
}
