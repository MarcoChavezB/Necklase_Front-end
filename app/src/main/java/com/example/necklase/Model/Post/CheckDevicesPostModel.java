package com.example.necklase.Model.Post;

import com.google.gson.annotations.SerializedName;

public class CheckDevicesPostModel {
    @SerializedName("count")
    private String numero;

    public CheckDevicesPostModel(String numero){
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }
}
