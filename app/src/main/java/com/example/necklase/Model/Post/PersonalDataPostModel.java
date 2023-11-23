package com.example.necklase.Model.Post;

import com.google.gson.annotations.SerializedName;

public class PersonalDataPostModel {
    @SerializedName("id")
    private String id;

    @SerializedName("nombre")
    private String name ;

    @SerializedName("email")
    private String email ;

    @SerializedName("apellido")
    private String lastName ;

    @SerializedName("Ndispositivos")
    private String nSensors ;

    public PersonalDataPostModel(String id) {
        this.id = id;
    }

    public String getNombre(){return name;}
    public String getEmail(){return email;}
    public String getApellido(){return lastName;}
    public String getNSensores(){return nSensors;}
}
