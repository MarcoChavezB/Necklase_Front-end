package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.MVVM.Interactors.PetsInteractor;
import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.R;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.View.Adapter.PetsInfoAdapter;
import com.example.necklase.ViewModel.PetsViewModel;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.ArrayList;
import java.util.List;

public class activity_pets_info extends AppCompatActivity {

    private TextView cons;
    private RecyclerView rc;
    private LinearLayout btnback;
    private PetsInfoAdapter adapter;
    private PetsViewModel miview;
    private List<PetModel> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_info);

        VerificarToken.Verificar(this);

        rc = findViewById(R.id.recycler_view_dog_info);
        btnback = findViewById(R.id.goBack);
        cons = findViewById(R.id.textView2);

        rc.setLayoutManager(new LinearLayoutManager(this));

        miview = new ViewModelProvider(this).get(PetsViewModel.class);
        ViewModelTokenIns modelTokenIns = ViewModelTokenIns.getinstance();

        PetsInteractor pts = new PetsInteractor(activity_pets_info.this);
        pts.getPets(modelTokenIns.getId());
        pts.lista.observe(this, new Observer<List<PetModel>>() {
                @Override
                public void onChanged(List<PetModel> petModels) {
                    miview.PostData(petModels);
                }
        });

        miview.getLiveData().observe(this, new Observer<List<PetModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<PetModel> petModels) {

            if (petModels != null && !petModels.isEmpty()) {
                adapter = new PetsInfoAdapter(activity_pets_info.this, new PetsInfoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(PetModel pet) {
                        Intent intent = new Intent(activity_pets_info.this, Activity_Pet_Info_Individual.class);
                        intent.putExtra("pet",pet);
                        startActivity(intent);
                    }
                }, petModels);

                rc.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(activity_pets_info.this, "No Data To Show", Toast.LENGTH_SHORT);
            }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_pets_info.this, navbar.class);
                startActivity(intent);
            }
        });

        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_pets_info.this, activity_register_pet_only.class);
                startActivity(intent);
            }
        });

    }
}