package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.necklase.Model.Get.Device;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.DeviceSync;

import java.util.ArrayList;
import java.util.List;


public class activity_anadir_dispositivo extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_dispositivo);

        btn = findViewById(R.id.boton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_anadir_dispositivo.this, activity_sincr_disp.class);
                startActivity(intent);
            }
        });

    }

}