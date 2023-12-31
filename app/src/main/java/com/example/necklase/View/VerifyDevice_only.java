package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.Get.BoleanModel;
import com.example.necklase.Model.Get.Device;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.CheckDevicesManagment;
import com.example.necklase.Model.Post.CheckDevicesPostModel;
import com.example.necklase.Model.RetrofitInterfaces.DispositivosInterface;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VerifyDevice_only extends AppCompatActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
    }
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Temanegro_Necklase);
        setContentView(R.layout.activity_verify_device);
        img = findViewById(R.id.circleanim);
        Animation scaleAnimation = AnimationUtils.loadAnimation(VerifyDevice_only.this, R.anim.light_blur_speed);
        img.startAnimation(scaleAnimation);

        VerificarToken.Verificar(this);

        CountDownTimer count = new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                String nom = getIntent().getStringExtra("code");
                if (nom != null) {
                    Log.e("code", nom);
                    String[] partes = nom.split("-");

                    if (partes.length != 3) {
                        Toast.makeText(VerifyDevice_only.this, "the device is not supported", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VerifyDevice_only.this, activity_sincr_disp_only.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Log.e("codigo", partes[2]);
                        checkDevices(partes[2]);
                    }
                } else {
                    Toast.makeText(VerifyDevice_only.this, "No code received", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyDevice_only.this, activity_sincr_disp_only.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
        count.start();

    }
    public void checkDevices(String codigo){
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        Call<BoleanModel> call = retrofit.create(DispositivosInterface.class).getDevice(codigo);
        call.enqueue(new Callback<BoleanModel>() {
            @Override
            public void onResponse(Call<BoleanModel> call, Response<BoleanModel> response) {
                int status = response.code();
                Log.e("estatus", String.valueOf(status));
                if(status == 404){
                    Toast.makeText(VerifyDevice_only.this,"This device doesn´t exist in the database", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyDevice_only.this, activity_anadir_dispositivo_only.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.body().getlinked()){
                    Toast.makeText(VerifyDevice_only.this,"Accepted device", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyDevice_only.this, Activity_pets_whothout_only.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("code", codigo);
                    startActivity(intent);
                } else{
                    Toast.makeText(VerifyDevice_only.this,"This device is already linked to an account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyDevice_only.this, activity_anadir_dispositivo_only.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<BoleanModel> call, Throwable t) {
                Toast.makeText(VerifyDevice_only.this,"Server Error",  Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VerifyDevice_only.this, activity_anadir_dispositivo_only.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }
}