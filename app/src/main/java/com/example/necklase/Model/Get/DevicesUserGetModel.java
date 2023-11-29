package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class DevicesUserGetModel {
    @SerializedName("id")
    private String id;

    public DevicesUserGetModel(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
