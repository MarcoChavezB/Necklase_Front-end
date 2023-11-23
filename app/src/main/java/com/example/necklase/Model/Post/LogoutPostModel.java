package com.example.necklase.Model.Post;

import com.google.gson.annotations.SerializedName;

public class LogoutPostModel {
    @SerializedName("id")
    private String id;

    public LogoutPostModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
