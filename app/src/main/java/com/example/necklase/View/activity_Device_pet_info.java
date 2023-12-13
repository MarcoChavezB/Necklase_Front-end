package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.MVVM.Interactors.DevicePetInfoInteractor;
import com.example.necklase.Model.Get.DevicePetModel;
import com.example.necklase.Model.Get.PetModel;
import com.example.necklase.R;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModel.DevicePetInfoViewModel;

public class activity_Device_pet_info extends AppCompatActivity {

    private DevicePetInfoViewModel viewmodel;
    private TextView nombredevice, typedevice, petname;
    private Button cambiarpet;
    private String iddevice;
    private LinearLayout lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_pet_info);

        VerificarToken.Verificar(this);

        nombredevice = findViewById(R.id.devicename);
        typedevice = findViewById(R.id.devicetype);
        petname = findViewById(R.id.textView3);
        cambiarpet = findViewById(R.id.cambiarpet);
        lin = findViewById(R.id.goBack222);

        viewmodel = new ViewModelProvider(this).get(DevicePetInfoViewModel.class);
        DevicePetInfoInteractor intec = new DevicePetInfoInteractor(this);

        Intent intent = getIntent();
        if (intent != null) {
            iddevice = (String) intent.getStringExtra("device");
        } else{
            Toast.makeText(this, "Error Intent Data", Toast.LENGTH_SHORT).show();
        }
        if (iddevice != null) {
            viewmodel.setiddevice(iddevice);
            intec.getData(iddevice);
        }

        viewmodel.getiddevice().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                iddevice = s;
            }
        });

        intec.info.observe(this, new Observer<DevicePetModel>() {
            @Override
            public void onChanged(DevicePetModel devicePetModel) {
                nombredevice.setText(devicePetModel.getCodigo());
                typedevice.setText(devicePetModel.getModelo());
                petname.setText(devicePetModel.getNombre());
            }
        });
        cambiarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Device_pet_info.this, activity_pets_device_change.class);
                intent.putExtra("iddevice",iddevice);
                startActivity(intent);
            }
        });

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_Device_pet_info.this, navbar.class);
                startActivity(intent);
            }
        });

    }
}