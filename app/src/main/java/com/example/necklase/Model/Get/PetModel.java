package com.example.necklase.Model.Get;

import com.google.gson.annotations.SerializedName;

public class PetModel {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
    public String getRaza() {
        return raza;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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
