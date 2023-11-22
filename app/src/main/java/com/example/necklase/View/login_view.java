package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.necklase.R;
import com.example.necklase.Router.Router;

public class login_view extends AppCompatActivity {

    ImageButton back;
    TextView signuptxt;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        back = findViewById(R.id.regresar);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login_view.this, Login_or_sign.class));
            }
        });
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