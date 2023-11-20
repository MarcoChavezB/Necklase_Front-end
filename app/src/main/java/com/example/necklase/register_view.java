package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.necklase.Router.Router;

public class register_view extends AppCompatActivity {

    TextView signintxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        signintxt = findViewById(R.id.signintxt);

        signintxt.setOnClickListener(v -> {
            Router.redirectTo(register_view.this, login_view.class);
        });
    }
}