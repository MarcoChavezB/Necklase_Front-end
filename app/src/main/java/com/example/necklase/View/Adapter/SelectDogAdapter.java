package com.example.necklase.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.Get.DeviceUserModel;
import com.example.necklase.Model.SelectDogM;
import com.example.necklase.R;
import com.example.necklase.View.SelectDog;

import java.util.List;

public class SelectDogAdapter extends RecyclerView.Adapter<SelectDogAdapter.ViewHolder> {

    List<SelectDogM> selectDogList;

    public SelectDogAdapter(List<SelectDogM> list){
        this.selectDogList = list;
    }

    @NonNull
    @Override
    public SelectDogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_select_dog_holder_desing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectDogAdapter.ViewHolder holder, int position) {
        SelectDogM selectDog = selectDogList.get(position);
        holder.setData(selectDog);
    }

    public void setDeviceUserList(List<DeviceUserModel> selectDogList){
        selectDogList.clear();
        selectDogList.addAll(selectDogList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return selectDogList.size();
    }

    TextView name, model;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            model = itemView.findViewById(R.id.model);
        }

        public void setData(SelectDogM selectDog){
            name.setText(selectDog.getName());
            model.setText(selectDog.getModel());
        }
    }
}
