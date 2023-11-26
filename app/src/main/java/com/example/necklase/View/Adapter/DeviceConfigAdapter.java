package com.example.necklase.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.Get.Device;
import com.example.necklase.R;

import java.util.List;

public class DeviceConfigAdapter extends RecyclerView.Adapter<DeviceConfigAdapter.DevicesAdapterHolder> {

    private List<Device> listadispositivos;
    private LayoutInflater inflater;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Device dispositivo);
    }

    public DeviceConfigAdapter(Context context, OnItemClickListener listener, List<Device> listadispositivos) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.listadispositivos = listadispositivos;
    }

    @Override
    public DevicesAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_item_device, parent, false);
        return new DevicesAdapterHolder(itemView);
    }


    @Override
    public void onBindViewHolder(DevicesAdapterHolder holder, int position) {
        Device device = listadispositivos.get(position);
        holder.bind(device, listener);
    }

    @Override
    public int getItemCount() {
        return listadispositivos.size();
    }

    public static class DevicesAdapterHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvModelo;
        private TextView tvCodigo;
        private Button btn;
        public DevicesAdapterHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tipodedispositivo);
            btn = itemView.findViewById(R.id.configurar);
            tvCodigo = itemView.findViewById(R.id.nombredeldispo);
        }
        public void bind(Device device, OnItemClickListener listener) {
            tvNombre.setText(device.getModelo());
            btn.setOnClickListener(v -> listener.onItemClick(device));
            tvCodigo.setText(device.getCodigo());
        }
    }
}
