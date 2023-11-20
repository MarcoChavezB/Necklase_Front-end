package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.necklase.Router.Router;

public class register_view extends AppCompatActivity {

    private ImageButton back;
    TextView signintxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        back = findViewById(R.id.regresar);
        signintxt = findViewById(R.id.signintxt);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register_view.this, Login_or_sign.class));
            }
        });
        signintxt.setOnClickListener(v -> {
            Router.redirectTo(register_view.this, login_view.class);
        });
    }
}