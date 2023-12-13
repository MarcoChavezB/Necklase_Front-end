package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.necklase.MVVM.Interactors.RegisterPetInteractor;
import com.example.necklase.Model.Post.RegisterPetModel;
import com.example.necklase.R;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModel.RegisterPetViewModel;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.Objects;

public class activity_register_pet_only extends AppCompatActivity {

    private EditText nombre, raza;
    private Spinner genero;
    private Button registrar;
    private RegisterPetViewModel viewm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);
        VerificarToken.Verificar(this);

        registrar = findViewById(R.id.botonregistrar);
        nombre = findViewById(R.id.nombreinfo);
        raza = findViewById(R.id.Razainfo);
        genero = findViewById(R.id.generoinfo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter);
        String codee = getIntent().getStringExtra("code");
        viewm = new ViewModelProvider(this).get(RegisterPetViewModel.class);
        viewm.setCode(codee);

        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                viewm.setGenre(selectedItem);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(activity_register_pet_only.this, "Select a genre", Toast.LENGTH_SHORT).show();
            }
        });

        viewm.getGenre().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (Objects.equals(s, "Female")){
                    genero.setSelection(1);
                } else if (Objects.equals(s, "Male")){
                    genero.setSelection(0);
                }
            }
        });

        ViewModelTokenIns modelTokenIns = ViewModelTokenIns.getinstance();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().length() <= 5){
                    Toast.makeText(activity_register_pet_only.this, "the name must be at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if (raza.getText().toString().length() <= 3){
                    Toast.makeText(activity_register_pet_only.this, "the bread must be at least 4 characters", Toast.LENGTH_SHORT).show();
                } else if (genero.getSelectedItem().toString().equals(null) || genero.getSelectedItem().toString().equals("")){
                    Toast.makeText(activity_register_pet_only.this, "Select a genre", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterPetInteractor nv = new RegisterPetInteractor(activity_register_pet_only.this);
                    nv.RegisterPetOnly(nombre.getText().toString(), raza.getText().toString(), genero.getSelectedItem().toString() ,modelTokenIns.getId());
                }
            }
        });

    }
}