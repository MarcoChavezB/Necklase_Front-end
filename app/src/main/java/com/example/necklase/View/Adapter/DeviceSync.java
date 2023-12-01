package com.example.necklase.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.Get.Device;
import com.example.necklase.R;

import java.util.List;

public class DeviceSync extends RecyclerView.Adapter<DeviceSync.DevicesAdapterHolder> {

    private List<Device> listadispositivos;
    private LayoutInflater inflater;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Device dispositivo);
    }

    public DeviceSync(Context context, OnItemClickListener listener, List<Device> listadispositivos) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.listadispositivos = listadispositivos;
        this.context = context;
    }

    @Override
    public DevicesAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_item_bluetooth, parent, false);
        return new DevicesAdapterHolder(itemView, this.context);
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
        private TextView tvModelo;
        private ImageButton btn;
        private ImageView img;
        private Context context;
        public DevicesAdapterHolder(View itemView, Context context) {
            super(itemView);
            tvModelo = itemView.findViewById(R.id.modelo2);
            btn = itemView.findViewById(R.id.sig);
            img = itemView.findViewById(R.id.circleanim);
            this.context = context;
        }
        public void bind(Device device, OnItemClickListener listener) {
            tvModelo.setText(device.getModelo());
            btn.setOnClickListener(v -> listener.onItemClick(device));
            Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.light_blur_blue);
            img.startAnimation(scaleAnimation);
        }
    }
}
