package com.example.necklase.Model.Get;
import com.google.gson.annotations.SerializedName;
import java.io.Serial;
public class AirModel {

    @SerializedName("nivel")
    private int nivel;

    @SerializedName("device")
    private String device;

    public void AirModel(String device){
        this.device = device;
    }

    public int getNivel(){
        return nivel;
    }

}
