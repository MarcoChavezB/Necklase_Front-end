package com.example.necklase.Model.Get;

import java.io.Serializable;

public class DevicePetModel implements Serializable {
    private String modelo;

    public DevicePetModel(String modelo, String codigo, String nombre) {
        this.modelo = modelo;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    private String codigo;

    public String getModelo() {
        return modelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    private String nombre;
}
