package com.example.necklase.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.necklase.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class activity_anadir_dispositivo extends AppCompatActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }

    private Button btn;
    private final int REQUEST_CODE_LOCATION = 2929181;
    private final int REQUEST_ENABLE_BT = 5453451;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_dispositivo);

        btn = findViewById(R.id.boton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    if (ActivityCompat.checkSelfPermission(activity_anadir_dispositivo.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        ActivityCompat.requestPermissions(activity_anadir_dispositivo.this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_ENABLE_BT);
                        return;
                    }
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                } else {
                    verificarYsolicitarPermisosUbicacion();
                }
            }
        });
    }

    private void verificarYsolicitarPermisosUbicacion() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, locationSettingsResponse -> {
            verificarPermisosUbicacion();
        });

        task.addOnFailureListener(this, e -> {
            if (e instanceof ResolvableApiException) {
                Toast.makeText(activity_anadir_dispositivo.this, "turn on your location", Toast.LENGTH_SHORT).show();
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(activity_anadir_dispositivo.this, REQUEST_CODE_LOCATION);
                } catch (IntentSender.SendIntentException sendEx) {
                }
            }
        });
    }

    private void verificarPermisosUbicacion() {
        List<String> permisosNecesarios = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permisosNecesarios.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (!permisosNecesarios.isEmpty()) {
            ActivityCompat.requestPermissions(this, permisosNecesarios.toArray(new String[0]), REQUEST_CODE_LOCATION);
            Toast.makeText(activity_anadir_dispositivo.this, "accept all permissions", Toast.LENGTH_SHORT).show();
        } else {
            iniciarSiguienteActividad();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                verificarYsolicitarPermisosUbicacion();
            } else {
                Toast.makeText(activity_anadir_dispositivo.this, "turn on your bluetooth", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void iniciarSiguienteActividad() {
        Intent intent = new Intent(this, activity_sincr_disp.class);
        startActivity(intent);
    }

}