package com.example.necklase.Model.Get;

import java.io.Serializable;

public class LedModel implements Serializable {
    public boolean isOn() {
        return on;
    }

    private boolean on;

    public LedModel(boolean on) {
        this.on = on;
    }
}
