package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CountDownTimer count = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {

                String token = ViewModelTokenIns.getinstance().token();

                if (token != null && !token.isEmpty()) {

                    if (isTokenExprired(token)) {
                        Router.redirectTo(MainActivity.this, Main_acept_permisos.class);
                    } else {
                        Router.redirectTo(MainActivity.this, navbar.class);
                    }
                } else {
                    Router.redirectTo(MainActivity.this, Main_acept_permisos.class);
                }
                
            }
        };
        count.start();
    }

    public boolean isTokenExprired(String token ){
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expirationDate = jwt.getExpiresAt();
            return expirationDate != null && expirationDate.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}