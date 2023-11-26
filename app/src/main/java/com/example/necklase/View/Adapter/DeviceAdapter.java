package com.example.necklase.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.Get.Device;
import com.example.necklase.R;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    List<Device> devices;
    public DeviceAdapter(List<Device> devices){
        this.devices = devices;
    }

    @NonNull
    @Override
    public DeviceAdapter.DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_dispositivo_recycler_holder, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceAdapter.DeviceViewHolder holder, int position) {
        Device device = devices.get(position);
        holder.setData(device);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    TextView name;
    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }

        public void setData(Device device){
            name.setText(device.getModelo());
        }
    }
}
