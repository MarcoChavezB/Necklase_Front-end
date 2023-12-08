package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Extras.NotificationHelper;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Temanegro_Necklase);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.circleanim);
        Animation scaleAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.light_blur_speed);
        img.startAnimation(scaleAnimation);
        CountDownTimer count = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {

                SharedPreferences prefs = MainActivity.this.getSharedPreferences("loginPrefs", MODE_PRIVATE);
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