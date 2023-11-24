package com.example.necklase.Model.Post;
import com.google.gson.annotations.SerializedName;

public class NdispositivosModel {
    @SerializedName("id")
    private String id;

    @SerializedName("Ndispositivos")
    private String Ndispositivos;

    public NdispositivosModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getNdispositivos() {return Ndispositivos;}
}
