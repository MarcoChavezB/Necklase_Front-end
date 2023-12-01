package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.necklase.Model.SelectDogM;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.SelectDogAdapter;
import com.example.necklase.ViewModel.SelectDogViewModel;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.ArrayList;
import java.util.List;

public class SelectDog extends AppCompatActivity {


    RecyclerView recyclerV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dog);
        recyclerV = findViewById(R.id.recyclerV);

        SelectDogAdapter selectDogAdapter = new SelectDogAdapter(new ArrayList<>(), this);
        recyclerV.setAdapter(selectDogAdapter);

        recyclerV.setLayoutManager(new LinearLayoutManager(this));
        recyclerV.setHasFixedSize(true);

        SelectDogViewModel selectDogViewModel = new ViewModelProvider(this).get(SelectDogViewModel.class);

        selectDogViewModel.getDeviceUserListLiveData().observe(this, deviceUserModels -> {
            selectDogAdapter.setDeviceUserList(deviceUserModels);
        });

        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String token = preferences.getString("token", null);


        String id = JwtUtils.decode(token).getSubject();
        Toast.makeText(this, "Token de usuario recycler" + id, Toast.LENGTH_SHORT).show();

        selectDogViewModel.getDeviceUser(id);
    }

}