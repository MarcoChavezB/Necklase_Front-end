package com.example.necklase.Model.Post;

import com.google.gson.annotations.SerializedName;

public class MyPetPostModel {
    @SerializedName("id")
    private String id;

    @SerializedName("nombre")
    private String name ;

    @SerializedName("raza")
    private String raza;

    @SerializedName("genero")
    private String genero;

    @SerializedName("dueño")
    private String dueño;

    @SerializedName("collar")
    private String collar;

    @SerializedName("codigo")
    private String codigo;

    public MyPetPostModel(String id) {
        this.id = id;
    }

    public String getNombre(){return name;}
    public String getRaza(){return raza;}
    public String getGenero(){return genero;}
    public String getDueño(){return dueño;}
    public String getCollar(){return collar;}
    public String getCodigo(){return codigo;}

}
