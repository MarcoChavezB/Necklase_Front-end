package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.R;
import com.example.necklase.Router.Router;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CountDownTimer count = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                SharedPreferences prefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                String token = prefs.getString("token", null);


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