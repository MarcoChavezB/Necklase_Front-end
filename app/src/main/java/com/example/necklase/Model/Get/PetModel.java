package com.example.necklase.Model.Get;
import java.io.Serializable;

public class PetModel implements Serializable {
    public String getId() {
        return id;
    }

    public String getName() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public String getGenero() {
        return genero;
    }

    public PetModel(String id, String nombre, String raza, String genero){
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.genero = genero;
    }

    private String id;

    private String nombre ;

    private String raza;

    private String genero;

}
