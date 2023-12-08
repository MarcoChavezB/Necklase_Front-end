package com.example.necklase.TokenValidator;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.View.login_view;


public class VerificarToken {
    public static void Verificar(Context contex){
        SharedPreferences prefs = contex.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String token = prefs.getString("token", null);
        DecodedJWT decodedJWT = JwtUtils.decode(token);

        if (decodedJWT == null) {
            Intent intent = new Intent(contex, login_view.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contex.startActivity(intent);
            Toast.makeText(contex, "The token is expired", Toast.LENGTH_SHORT).show();
        }
    }
}
