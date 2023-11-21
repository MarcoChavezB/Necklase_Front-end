package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import com.example.necklase.Router.Router;

import java.util.ArrayList;
import java.util.List;

public class Main_acept_permisos extends AppCompatActivity {
    Button aceptar, denegar;
    int REQUEST_PHONE_LOCATION = 126462626;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_acept_permisos);

        aceptar = findViewById(R.id.aceptar);
        denegar = findViewById(R.id.denegar);

        aceptar.setOnClickListener(v -> {
            if (solicitarPermisos()){
                Router.redirectTo(Main_acept_permisos.this, Login_or_sign.class);
            }
        });

        denegar.setOnClickListener(v -> {
            Router.redirectTo(Main_acept_permisos.this, MainActivity.class);
        });
    }

    public boolean solicitarPermisos() {
        List<String> permisos = new ArrayList<>();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PHONE_LOCATION);
            permisos.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if(permisos.isEmpty()){
            return true;
        }

        return false;
    }
}