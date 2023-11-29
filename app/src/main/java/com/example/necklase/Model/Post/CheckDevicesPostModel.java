package com.example.necklase.Model.Post;

import com.google.gson.annotations.SerializedName;

public class CheckDevicesPostModel {
    @SerializedName("id")
    private String id;

    public CheckDevicesPostModel(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
