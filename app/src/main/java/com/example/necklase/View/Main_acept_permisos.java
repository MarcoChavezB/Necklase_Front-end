package com.example.necklase.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.necklase.R;
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                solicitarPermisos();
            }
        });

        denegar.setOnClickListener(v -> {
            Router.redirectTo(Main_acept_permisos.this, MainActivity.class);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void solicitarPermisos() {
        List<String> permisosNecesarios = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.BLUETOOTH);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.BLUETOOTH_ADMIN);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.BLUETOOTH_CONNECT);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            permisosNecesarios.add(Manifest.permission.POST_NOTIFICATIONS);
        }

        if (!permisosNecesarios.isEmpty()) {
            ActivityCompat.requestPermissions(this, permisosNecesarios.toArray(new String[0]), REQUEST_PHONE_LOCATION);

        } else {
            Router.redirectTo(Main_acept_permisos.this, Login_or_sign.class);
        }
    }



}