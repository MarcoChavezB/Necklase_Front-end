package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class caloriasModel {


    @SerializedName("bmr")
    private String bmr;

    @SerializedName("activeCalories")
    private String activeCalories;

    @SerializedName("totalCalories")
    private String totalCalories;

    @SerializedName("deviceCode")
    private String deviceCode;

    @SerializedName("peso")
    private String peso;

    public caloriasModel(String deviceCode, String peso) {
        this.deviceCode = deviceCode;
        this.peso = peso;
    }

    public String getBmr() {
        return bmr;
    }
    public String getTotalCalories() {
        return totalCalories;
    }
    public String getActiveCalories() {
        return activeCalories;
    }

}
