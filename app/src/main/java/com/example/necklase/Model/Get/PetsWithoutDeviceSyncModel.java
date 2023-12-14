package com.example.necklase.Model.Get;

public class PetsWithoutDeviceSyncModel {
    public PetsWithoutDeviceSyncModel(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    private String id;

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    private String nombre;

}
