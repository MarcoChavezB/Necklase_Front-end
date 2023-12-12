package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.necklase.R;

public class activity_register_pet extends AppCompatActivity {

    private EditText nombre, raza;
    private Spinner genero;
    private Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        registrar = findViewById(R.id.botonregistrar);
        nombre = findViewById(R.id.nombreinfo);
        raza = findViewById(R.id.Razainfo);
        genero = findViewById(R.id.generoinfo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = getIntent().getStringExtra("code");



            }
        });

    }
}