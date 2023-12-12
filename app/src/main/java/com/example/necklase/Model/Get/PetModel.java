package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class PetModel {
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRaza() {
        return raza;
    }

    public String getGenero() {
        return genero;
    }

    public PetModel(String id, String nombre, String raza, String genero){
        this.id = id;
        this.name = nombre;
        this.raza = raza;
        this.genero = genero;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("nombre")
    private String name ;

    @SerializedName("raza")
    private String raza;

    @SerializedName("genero")
    private String genero;

}
