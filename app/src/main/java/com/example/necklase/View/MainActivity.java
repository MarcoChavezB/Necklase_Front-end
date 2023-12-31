package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.MVVM.Interactors.AnaliticsInteractor;
import com.example.necklase.MVVM.Interactors.HomeInteractor;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Temanegro_Necklase);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.circleanim);
        Animation scaleAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.light_blur_speed);
        img.startAnimation(scaleAnimation);

        ViewModelTokenIns viewModelTokenIns = ViewModelTokenIns.getinstance();
        viewModelTokenIns.settoken(this);
        HomeInteractor interactor = new HomeInteractor(this);

        SharedPreferences.Editor userInfo = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
        userInfo.putString("id", ViewModelTokenIns.getinstance().getId());
        userInfo.apply();

        interactor.setCorrectDevice(ViewModelTokenIns.getinstance().getId());

        CountDownTimer count = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            public void onFinish() {

                SharedPreferences prefs = MainActivity.this.getSharedPreferences("loginPrefs", MODE_PRIVATE);
                String token = prefs.getString("token", null);

                if (token != null && !token.isEmpty()) {
                    if (isTokenExprired(token)) {
                        Intent intent = new Intent(MainActivity.this, Main_acept_permisos.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, activity_bienvenida.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, Main_acept_permisos.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
        count.start();
    }

    public boolean isTokenExprired(String token ){
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expirationDate = jwt.getExpiresAt();
            return expirationDate != null && expirationDate.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean isAllPermissionsGranted() {
        String[] requiredPermissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.POST_NOTIFICATIONS
        };

        for (String permission : requiredPermissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

}