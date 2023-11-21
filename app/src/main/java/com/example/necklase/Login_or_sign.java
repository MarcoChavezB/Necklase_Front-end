package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_or_sign extends AppCompatActivity implements View.OnClickListener {

    private Button login, sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_sign);
        login = findViewById(R.id.login);
        sign = findViewById(R.id.signup);

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