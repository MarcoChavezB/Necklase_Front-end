package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class ForeModel {
    @SerializedName("coords")
    private String coords;

    @SerializedName("CitiLocation")
    private String CitiLocation;

    @SerializedName("CitiRegion")
    private String CitiRegion;

    @SerializedName("temp")
    private String temp;

    @SerializedName("maxTemp")
    private String maxtemp;

    @SerializedName("minTemp")
    private String mintemp;

    @SerializedName("dailyChanceOfRain")
    private String dailyChanceOfRain;

    @SerializedName("feelLike")
    private String feelsLike;

    public ForeModel(String coords){
        this.coords = coords;
    }

    public String getCitiLocation() {
        return CitiLocation;
    }
    public String getCitiRegion() {
        return CitiRegion;
    }
    public String getTemp() {
        return temp;
    }
    public String getMaxtemp() {
        return maxtemp;
    }
    public String getMintemp() {
        return mintemp;
    }
    public String getDailyChanceOfRain() {
        return dailyChanceOfRain;
    }
    public String getFeelsLike() {
        return feelsLike;
    }
    public String getCoords() {
        return coords;
    }

}
