package com.example.necklase.ViewModelToken;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.Token.JwtUtils;

public class ViewModelTokenIns extends AndroidViewModel {

    private String token;
    private String userId;
    private DecodedJWT decodedJWT;
    public ViewModelTokenIns() {
        super();
        SharedPreferences prefs = getApplication().getApplicationContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        token = prefs.getString("token", null);
        decodedJWT = JwtUtils.decode(token);
        userId = decodedJWT.getSubject();
    }

    public String getId() {
        return this.userId;
    }
    public DecodedJWT getDecodedJWT() {
        return this.decodedJWT;
    }
    public String token () {
        return this.token;
    }
}
