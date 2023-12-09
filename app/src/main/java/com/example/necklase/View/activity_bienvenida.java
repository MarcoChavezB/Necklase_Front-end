package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.CheckDevicesManagment;
import com.example.necklase.Model.Post.CheckDevicesPostModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import android.content.Intent;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class activity_bienvenida extends AppCompatActivity {

    private Button btn;
    private String id;
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);


        btn = findViewById(R.id.boton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModelTokenIns.getinstance();
                ViewModelTokenIns.settoken(activity_bienvenida.this);

                SharedPreferences prefs = activity_bienvenida.this.getSharedPreferences("loginPrefs", MODE_PRIVATE);
                String token = prefs.getString("token", null);
                DecodedJWT decodedJWT = JwtUtils.decode(token);
                DecodedJWT decode = JwtUtils.decode(token);

                if (decode != null) {
                    id = decode.getSubject();
                    Log.e("ID bienvenida token", "El id es valido");
                    checkDevices();
                } else {
                    Log.e("ID bienvenida token", "El token es nulo o inválido");
                }

            }
        });
    }
    public void checkDevices(){
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        CheckDevicesManagment checkDevicesManagment = new CheckDevicesManagment(retrofit);
        checkDevicesManagment.getData(this.id, new Callback<CheckDevicesPostModel>() {
            @Override
            public void onResponse(Call<CheckDevicesPostModel> call, Response<CheckDevicesPostModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getNumero() == null){
                        Toast.makeText(activity_bienvenida.this, "Error al cargar dispositivos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Integer.parseInt(response.body().getNumero()) == 0){
                        Router.redirectTo(activity_bienvenida.this, activity_anadir_dispositivo.class);
                    }else{
                        Router.redirectTo(activity_bienvenida.this, navbar.class);
                    }
                }else{
                    Toast.makeText(activity_bienvenida.this, "Error al ingresar dispositivos" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CheckDevicesPostModel> call, Throwable t) {
            }
        });
    }

    private void CountDownTimer() {
        new CountDownTimer(2000, 2000) {
            public void onFinish() {
            }
            public void onTick(long millisUntilFinished) {
            }
        }.start();
    }

}