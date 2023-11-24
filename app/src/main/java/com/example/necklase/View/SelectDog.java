package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.necklase.Model.Post.NdispositivosManagment;
import com.example.necklase.Model.Post.NdispositivosModel;
import com.example.necklase.Model.RetrofitApiModel;
import com.example.necklase.Model.RetrofitInterfaces.NdispositivosInterface;
import com.example.necklase.Model.SelectDogM;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.SelectDogAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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