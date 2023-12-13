package com.example.necklase.Model.Post;

import java.io.Serializable;

public class RegisterPetOnlyModel implements Serializable {
    private String  nombre, raza, genero, user_id;
    public RegisterPetOnlyModel( String user_id, String nombre, String raza, String genero){
        this.nombre = nombre;
        this.raza = raza;
        this.genero = genero;
        this.user_id = user_id;
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
