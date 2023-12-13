package com.example.necklase.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.Get.PetsWithoutDeviceSyncModel;
import com.example.necklase.R;

import java.util.List;

public class petswithoutAdapter extends RecyclerView.Adapter<petswithoutAdapter.ViewHolder> {


    private List<PetsWithoutDeviceSyncModel> listadepets;
    private LayoutInflater inflater;
    private Context context;
    private petswithoutAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(PetsWithoutDeviceSyncModel pet);
    }

    public void UpdateList(List<PetsWithoutDeviceSyncModel> pet){
        this.listadepets = pet;
    }

    public petswithoutAdapter(Context context, petswithoutAdapter.OnItemClickListener listener, List<PetsWithoutDeviceSyncModel> listadepets) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.listadepets = listadepets;
        this.context = context;
    }

    @Override
    public petswithoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_pets_item, parent, false);
        return new petswithoutAdapter.ViewHolder(itemView, this.context);
    }


    @Override
    public void onBindViewHolder(petswithoutAdapter.ViewHolder holder, int position) {
        PetsWithoutDeviceSyncModel pet = listadepets.get(position);
        holder.bind(pet, listener);
    }

    @Override
    public int getItemCount() {
        return listadepets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvnombre;
        private ImageButton btn;
        private ImageView img;
        private Context context;
        public ViewHolder(View itemView, Context context) {
            super(itemView);
            tvnombre = itemView.findViewById(R.id.nameDOG);
            btn = itemView.findViewById(R.id.botonDOG);
            img = itemView.findViewById(R.id.imageViewDOG);
            this.context = context;
        }
        public void bind(PetsWithoutDeviceSyncModel pet, petswithoutAdapter.OnItemClickListener listener) {
            tvnombre.setText(pet.getNombre());
            btn.setOnClickListener(v -> listener.onItemClick(pet));
            Animation scaleAnimation = AnimationUtils.loadAnimation(context, R.anim.dog_animation);
            img.startAnimation(scaleAnimation);
        }
    }

}
