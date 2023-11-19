package com.example.necklase.Model;

public class history {
    public String nameHistory;
    public String data;

    public history(String nameHistory, String data){
        this.nameHistory = nameHistory;
        this.data = data;
    }

    public String getNameHistory(){
        return nameHistory;
    }

    public String getData(){
        return data;
    }
}
