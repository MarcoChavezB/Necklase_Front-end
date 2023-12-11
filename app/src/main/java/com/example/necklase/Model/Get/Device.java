package com.example.necklase.Model.Get;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Device implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("modelo")
    private String modelo;
    @SerializedName("codigo")
    private String codigo;


    public Device(int id, String modelo, String codigo) {
        this.id = id;
        this.modelo = modelo;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
