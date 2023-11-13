package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.necklase.Router.Router;

public class Main_acept_permisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_acept_permisos);

        CountDownTimer count = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Router.redirectTo(Main_acept_permisos.this, Collar_managment.class);
            }
        };
        count.start();
    }
}