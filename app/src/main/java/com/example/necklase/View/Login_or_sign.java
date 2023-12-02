package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.necklase.R;

public class Login_or_sign extends AppCompatActivity implements View.OnClickListener {

    private Button login, sign;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Temanegro_Necklase);
        setContentView(R.layout.activity_login_or_sign);
        login = findViewById(R.id.login);
        sign = findViewById(R.id.signup);
        img = findViewById(R.id.circleanim);
        Animation scaleAnimation = AnimationUtils.loadAnimation(Login_or_sign.this, R.anim.light_blur_blue_slow);
        img.startAnimation(scaleAnimation);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_or_sign.this, register_view.class));
            }
        });
        sign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_or_sign.this, login_view.class));
            }
        });

    }

    public void onClick(View v) {

    }
}