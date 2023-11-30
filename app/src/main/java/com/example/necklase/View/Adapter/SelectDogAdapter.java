package com.example.necklase.View.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.necklase.Model.Get.DeviceUserModel;
import com.example.necklase.Model.SelectDogM;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.View.SelectDog;
import com.example.necklase.View.login_view;
import com.example.necklase.View.navbar;

import java.util.List;

public class SelectDogAdapter extends RecyclerView.Adapter<SelectDogAdapter.ViewHolder> {

    private List<DeviceUserModel> deviceUserList;
    private Context context;

    public SelectDogAdapter(List<DeviceUserModel> list, Context context){
        this.deviceUserList = list;
        this.context = context;
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
        DeviceUserModel selectDog = deviceUserList.get(position);
        holder.setData(selectDog);
    }

    public void setDeviceUserList(List<DeviceUserModel> newList){
        deviceUserList.clear();
        deviceUserList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return deviceUserList.size();
    }

    TextView name, model;
    LinearLayout next;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            model = itemView.findViewById(R.id.model);
            next = itemView.findViewById(R.id.next);
        }

        public void setData(DeviceUserModel selectDog){
            name.setText(selectDog.getModelo());
            model.setText(selectDog.getCodigo());

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String id = deviceUserList.get(position).getId();

                    SharedPreferences.Editor editor = context.getSharedPreferences("deviceID", MODE_PRIVATE).edit();
                    editor.putString("id", id);
                    editor.apply();

                    Toast.makeText(context, "Perro seleccionado: " + id, Toast.LENGTH_SHORT).show();
                    Router.redirectTo(context, navbar.class);
                }
            });
        }
    }
}
