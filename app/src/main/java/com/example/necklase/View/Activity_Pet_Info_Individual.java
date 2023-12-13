package com.example.necklase.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.MVVM.Interactors.PetsInteractor;
import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.R;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModel.petsinfoindividualViewModel;

public class Activity_Pet_Info_Individual extends AppCompatActivity {

    private Button btneliminar;
    private LinearLayout btnback;
    private TextView name, bread, genre, nombre;
    private petsinfoindividualViewModel viewmodel;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info_individual);

        VerificarToken.Verificar(this);

        btneliminar = findViewById(R.id.eliminarpet);
        name = findViewById(R.id.nameinput);
        bread = findViewById(R.id.breadinput);
        genre = findViewById(R.id.Genreinput);
        btnback = findViewById(R.id.goBack2);
        nombre = findViewById(R.id.textView3);
        viewmodel = new ViewModelProvider(this).get(petsinfoindividualViewModel.class);

        PetModel pet = null;
        Intent intent = getIntent();
        if (intent != null) {
            pet = (PetModel) intent.getSerializableExtra("pet");
        } else{
            Toast.makeText(this, "Error Intent Data", Toast.LENGTH_SHORT).show();
        }
        if (pet != null) {
            viewmodel.setData(pet);
        }


        viewmodel.getData().observe(this, new Observer<PetModel>() {
            @Override
            public void onChanged(PetModel petModel) {
                name.setText(petModel.getName());
                bread.setText(petModel.getRaza());
                genre.setText(petModel.getGenero());
                nombre.setText(petModel.getName());
                id = petModel.getId();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Pet_Info_Individual.this, activity_pets_info.class);
                startActivity(intent);
            }
        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Pet_Info_Individual.this);
                builder.setTitle("Confirmación");
                builder.setMessage("¿Are you sure about eliminating this pet?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PetsInteractor inte = new PetsInteractor(Activity_Pet_Info_Individual.this);
                        inte.deletepet(id);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}