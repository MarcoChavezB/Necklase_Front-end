package com.example.necklase.View;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.necklase.Model.Get.Device;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.DeviceSync;
import android.bluetooth.BluetoothAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity_sincr_disp extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private RecyclerView recyclerView;
    private DeviceSync adapter;
    private List<Device> listadispositivos;
    private BluetoothAdapter bluetoothAdapter;
    int REQUEST_PHONE_LOCATION = 126462626;
    private Button btnReiniciarBusqueda;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincr_disp);

        btnReiniciarBusqueda = findViewById(R.id.seach);
        btnReiniciarBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarBusquedaBluetooth();
            }
        });

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Log.e("BluetoothDebug", "Este dispositivo no soporta Bluetooth");
        } else if (!bluetoothAdapter.isEnabled()) {
            Log.d("BluetoothDebug", "Bluetooth no está habilitado, solicitando activación...");
        } else {
            Log.d("BluetoothDebug", "Bluetooth está habilitado");
        }

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                        REQUEST_ENABLE_BT);
                return;
            }
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                checkBluetoothPermissionsAndDiscover();
            }
        }


        recyclerView = findViewById(R.id.recycler_view_sync_device);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listadispositivos = new ArrayList<>();

        adapter = new DeviceSync(this, new DeviceSync.OnItemClickListener() {
            @Override
            public void onItemClick(String code) {
                Intent intent = new Intent(activity_sincr_disp.this, VerifyDevice.class);
                intent.putExtra("code", code);
                startActivity(intent);
            }
        }, listadispositivos);

        recyclerView.setAdapter(adapter);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.S)
        @SuppressLint("NotifyDataSetChanged")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("BluetoothDebug", "BroadcastReceiver onReceive: Acción recibida - " + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                    String deviceName = device.getName();
                    String deviceAddress = device.getAddress();
                    if (deviceName == null) {
                        deviceName = "Desconocido";
                    }
                    if (deviceAddress == null) {
                        deviceAddress = "Desconocido";
                    }
                    Log.e("BluetoothDebug", "Dispositivo Bluetooth encontrado: " + deviceName + ", " + deviceAddress);
                }
                String deviceName = device.getName();
                if (deviceName == null) {
                    deviceName = "Desconocido";
                }
                String deviceAddress = device.getAddress();
                if (deviceAddress == null) {
                    deviceAddress = "Desconocido";
                }
                Log.e("Dispositivo encontrado", deviceName + " " + deviceAddress);
                listadispositivos.add(new Device(0, deviceName, deviceAddress));
                adapter.notifyDataSetChanged();
            }
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.S)
    private void checkBluetoothPermissionsAndDiscover() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_ENABLE_BT);
        } else {
            startBluetoothDiscovery();
        }
    }

    private void reiniciarBusquedaBluetooth() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        bluetoothAdapter.startDiscovery();
        Toast.makeText(this, "Reiniciando búsqueda Bluetooth", Toast.LENGTH_SHORT).show();
        listadispositivos.clear();
        adapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private void startBluetoothDiscovery() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    REQUEST_ENABLE_BT);
            return;
        }
        Log.e("BluetoothDebug", "Iniciando el descubrimiento de dispositivos Bluetooth...");
        bluetoothAdapter.startDiscovery();
        Log.e("discovery ", "Buscando dispositivos...");
    }

    private int REQUEST_CODE_LOCATION = 20220;

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_ENABLE_BT && grantResults.length > 0) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    startBluetoothDiscovery();
                }
            } else {
                Toast.makeText(this, "Todos los permisos de Bluetooth son necesarios", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("BluetoothDebug", "Desregistrando BroadcastReceiver");
        unregisterReceiver(receiver);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    REQUEST_ENABLE_BT);
            return;
        }
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
            Log.e("discovery ", "se detuvo buscar dispositivos...");
        }
    }

}