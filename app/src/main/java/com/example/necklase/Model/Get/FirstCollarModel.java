package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class FirstCollarModel {
    @SerializedName("id")
    private String id;

    FirstCollarModel(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
