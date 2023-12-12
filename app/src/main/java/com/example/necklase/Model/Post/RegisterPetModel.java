package com.example.necklase.Model.Post;

import java.io.Serializable;

public class RegisterPetModel implements Serializable {
    private String codigo, nombre, raza, genero, user_id;
    RegisterPetModel(String codigo, String nombre, String raza, String genero, String user_id){
        this.codigo = codigo;
        this.nombre = nombre;
        this.raza = raza;
        this.genero = genero;
        this.user_id = user_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public String getGenero() {
        return genero;
    }

    public String getUser_id() {
        return user_id;
    }
}
