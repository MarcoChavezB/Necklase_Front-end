package com.example.necklase.Model.Post;

import com.google.gson.annotations.SerializedName;

public class RegisterPostModel {
    @SerializedName("nombre")
    private String name ;

    @SerializedName("email")
    private String email ;

    @SerializedName("apellido")
    private String lastName ;

    @SerializedName("contraseña")
    private String password ;



    public RegisterPostModel(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}