package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.necklase.Model.Get.Device;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.DeviceSync;

import java.util.ArrayList;
import java.util.List;

public class activity_sincr_disp extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DeviceSync adapter;
    private List<Device> listadispositivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincr_disp);

        recyclerView = findViewById(R.id.recycler_view_sync_device);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listadispositivos = new ArrayList<>();
        listadispositivos.add(new Device(1, "Pechera", "9912ik2"));
        listadispositivos.add(new Device(2, "Pechera", "9912ik2"));
        adapter = new DeviceSync(this, new DeviceSync.OnItemClickListener() {
            @Override
            public void onItemClick(Device dispositivo) {
                Intent intent = new Intent(activity_sincr_disp.this, navbar.class);
                startActivity(intent);

            }
        }, listadispositivos);
        recyclerView.setAdapter(adapter);
    }
}