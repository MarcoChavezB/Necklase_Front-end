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

import com.example.necklase.MVVM.Interactors.DevicePetInfoInteractor;
import com.example.necklase.MVVM.Interactors.PetsInteractor;
import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.Model.Get.PetsWithoutDeviceSyncModel;
import com.example.necklase.R;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.View.Adapter.PetsInfoAdapter;
import com.example.necklase.View.Adapter.petswithoutAdapter;
import com.example.necklase.ViewModel.DevicePetInfoViewModel;
import com.example.necklase.ViewModel.PetsViewModel;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.ArrayList;
import java.util.List;

public class Activity_pets_whothout_only extends AppCompatActivity {

    private DevicePetInfoViewModel viewmodel;
    private TextView cons;
    private RecyclerView rc;
    private LinearLayout btnback;
    private petswithoutAdapter adapter;
    private String iddevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_info);

        VerificarToken.Verificar(this);

        rc = findViewById(R.id.recycler_view_dog_info);
        btnback = findViewById(R.id.goBack);
        cons = findViewById(R.id.textView2);
        rc.setLayoutManager(new LinearLayoutManager(this));

        viewmodel = new ViewModelProvider(this).get(DevicePetInfoViewModel.class);
        DevicePetInfoInteractor intec = new DevicePetInfoInteractor(this);

        Intent intent = getIntent();
        if (intent != null) {
            iddevice = (String) intent.getStringExtra("code");
        } else{
            Toast.makeText(this, "Error Intent Data", Toast.LENGTH_SHORT).show();
        }
        if (iddevice != null) {
            viewmodel.setiddevice(iddevice);

        }
        ViewModelTokenIns view = ViewModelTokenIns.getinstance();
        intec.getDataChange(view.getId());
        intec.pets.observe(this, new Observer<List<PetsWithoutDeviceSyncModel>>() {
            @Override
            public void onChanged(List<PetsWithoutDeviceSyncModel> petsWithoutDeviceSyncModels) {
                viewmodel.setListapetswithout(petsWithoutDeviceSyncModels);
            }
        });

        viewmodel.getiddevice().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                iddevice = s;
            }
        });


        viewmodel.getListapetswithout().observe(this, new Observer<List<PetsWithoutDeviceSyncModel>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<PetsWithoutDeviceSyncModel> petsWithoutDeviceSyncModels) {
                if (petsWithoutDeviceSyncModels != null && !petsWithoutDeviceSyncModels.isEmpty()) {
                    adapter = new petswithoutAdapter(Activity_pets_whothout_only.this, new petswithoutAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(PetsWithoutDeviceSyncModel pet) {
                            intec.vinculardevicepetonly(iddevice, pet.getId());
                        }
                    }, petsWithoutDeviceSyncModels);

                    rc.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Activity_pets_whothout_only.this, "No Data To Show", Toast.LENGTH_SHORT);
                }
            }
        });


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_pets_whothout_only.this, navbar.class);
                startActivity(intent);
            }
        });

        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_pets_whothout_only.this, activity_register_pet_only.class);
                startActivity(intent);
            }
        });

    }
}