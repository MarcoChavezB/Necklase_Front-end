package com.example.necklase.ViewModelToken;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.Interceptor.TokenProvider;
import com.example.necklase.Model.Token.JwtUtils;

public class ViewModelTokenIns implements TokenProvider {
    private static ViewModelTokenIns viewModelTokenIns;
    private static String token;
    private static String userId;


    private static String code;
    private static DecodedJWT decodedJWT;
    private ViewModelTokenIns() {
    }

    public static ViewModelTokenIns settoken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences codeDevice = context.getSharedPreferences("deviceInfo",MODE_PRIVATE);
        code = codeDevice.getString("deviceCode", null);
        token = prefs.getString("token", null);
        decodedJWT = JwtUtils.decode(token);

        if (decodedJWT != null) {
            userId = decodedJWT.getSubject();
            Log.e("El token", "el token es: " + decodedJWT);
        } else {
            Log.e("ViewModelTokenIns", "El token es nulo o inválido");
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public static synchronized ViewModelTokenIns getinstance() {
        if (viewModelTokenIns == null) {
            viewModelTokenIns = new ViewModelTokenIns();
        }
        return viewModelTokenIns;
    }

    public static ViewModelTokenIns clearToken(Context context){
        SharedPreferences.Editor editorRM = context.getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
        editorRM.remove("token");
        editorRM.apply();
        return null;
    }

    public String getId() {
        return userId;
    }
    public void setToken(String token) {
        ViewModelTokenIns.token = token;
        if(token != null){
            decodedJWT = JwtUtils.decode(ViewModelTokenIns.token);
        }
    }
    public DecodedJWT getDecodedJWT() {
        return decodedJWT;
    }
    public void setId(String userId) {
        ViewModelTokenIns.userId = userId;
    }
    public String token() {
        return token;
    }

    @Override
    public String getToken() {
        return token;
    }
}
