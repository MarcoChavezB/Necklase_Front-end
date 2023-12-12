package com.example.necklase.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.CarruselModel;
import com.example.necklase.R;

import java.util.List;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.ViewHolder> {
    private final List<CarruselModel> listaDatos;

    public CarruselAdapter(List<CarruselModel> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrusel_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarruselModel item = listaDatos.get(position);
        holder.itemText.setText(item.getTexto());
        holder.itemImage.setImageResource(item.getImage());
        holder.btn.setOnClickListener(item.getEvento());
        holder.btn.setText(item.getNombreboton());
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        ImageView itemImage;
        Button btn;

        ViewHolder(View view) {
            super(view);
            itemText = view.findViewById(R.id.texto);
            itemImage = view.findViewById(R.id.imagencarrusel);
            btn = view.findViewById(R.id.buttonLocate);
        }
    }
}
