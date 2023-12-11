package com.example.necklase.Model;

import android.app.Activity;
import android.view.View;

public class CarruselModel {

    public CarruselModel(String nombreboton, int image, View.OnClickListener evento, String texto){
        this.evento = evento;
        this.image = image;
        this.nombreboton = nombreboton;
        this.texto = texto;
    }
    private String nombreboton;
    private int image;

    public View.OnClickListener getEvento() {
        return evento;
    }

    public void setEvento(View.OnClickListener evento) {
        this.evento = evento;
    }

    private View.OnClickListener evento;


    private String texto;

    public String getNombreboton() {
        return nombreboton;
    }

    public void setNombreboton(String nombreboton) {
        this.nombreboton = nombreboton;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
