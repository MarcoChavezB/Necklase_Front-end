package com.example.necklase.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.R;

import java.util.List;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.ViewHolder> {

    List<history> historyList;

    public historyAdapter(List<history> historyList){
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public historyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_history_recycler_holder_desing,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyAdapter.ViewHolder holder, int position) {
        history history = historyList.get(position);
        holder.setData(history);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
    TextView name, data;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            data = itemView.findViewById(R.id.data);
        }

        public void setData(history history) {
            name.setText(history.getNameHistory());
            data.setText(history.getData());
        }
    }
}
