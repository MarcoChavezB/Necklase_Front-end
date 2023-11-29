package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.necklase.Model.SelectDogM;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.SelectDogAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectDog extends AppCompatActivity {


    RecyclerView recyclerV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dog);
        recyclerV = findViewById(R.id.recyclerV);


        List<SelectDogM> selectDogMList = new ArrayList<>();
        selectDogMList.add(new SelectDogM("Dog1", "Model1"));
        selectDogMList.add(new SelectDogM("Dog2", "Model2"));

        SelectDogAdapter selectDogAdapter = new SelectDogAdapter(selectDogMList);
        recyclerV.setAdapter(selectDogAdapter);

        recyclerV.setLayoutManager(new LinearLayoutManager(this));
        recyclerV.setHasFixedSize(true);

    }

}