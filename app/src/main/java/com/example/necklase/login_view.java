package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.necklase.Router.Router;

public class login_view extends AppCompatActivity {

    TextView signuptxt;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        signuptxt = findViewById(R.id.signuptxt);
        login = findViewById(R.id.login);

        signuptxt.setOnClickListener(v -> {
            Router.redirectTo(login_view.this, register_view.class);
        });

        login.setOnClickListener(v -> {
            Router.redirectTo(login_view.this, navbar.class);
        });
    }
}