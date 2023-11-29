package com.example.necklase.ViewModelToken;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.Token.JwtUtils;

public class ViewModelTokenIns {

    private static ViewModelTokenIns viewModelTokenIns;
    private static String token;
    private static String userId;
    private static DecodedJWT decodedJWT;
    private ViewModelTokenIns() {

    }

    public static ViewModelTokenIns settoken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        token = prefs.getString("token", null);
        decodedJWT = JwtUtils.decode(token);
        userId = decodedJWT.getSubject();
        Log.e("El token", "el token es: " + token);
        return null;
    }

    public static synchronized ViewModelTokenIns getinstance() {
        if (viewModelTokenIns == null) {
            viewModelTokenIns = new ViewModelTokenIns();
        }
        return viewModelTokenIns;
    }

    public String getId() {
        return userId;
    }
    public DecodedJWT getDecodedJWT() {
        return decodedJWT;
    }
    public String token() {
        return token;
    }
}
