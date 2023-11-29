package com.example.necklase.MVVM.Interactors;
import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Post.LoginManagment;
import com.example.necklase.Model.Post.LoginPostModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.Router.Router;
import com.example.necklase.View.navbar;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginInteractor {
    private Context context;
    public LoginInteractor(Context context){
           this.context = context;
    }

    public void login(String email, String password){

        RetrofitApiModel retro = new RetrofitApiModel(this.context);
        Retrofit retrofit = retro.provideRetrofit();
        LoginManagment loginManagment = new LoginManagment(retrofit);

        loginManagment.postData(email, password, new Callback<LoginPostModel>() {
            @Override
            public void onResponse(Call<LoginPostModel> call, Response<LoginPostModel> response) {

                SharedPreferences.Editor editor = context.getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                editor.putString("token", response.body().getToken());
                editor.apply();

                ViewModelTokenIns viewModelTokenIns = ViewModelTokenIns.getinstance();
                ViewModelTokenIns.settoken(context);


                SharedPreferences prefs = context.getSharedPreferences("loginPrefs", MODE_PRIVATE);
                String token = prefs.getString("token", null);


                if(token != null){
                    DecodedJWT decodedJWT = JwtUtils.decode(token);
                    if(decodedJWT != null){
                        String userId = decodedJWT.getSubject();
                    }
                }
                Router.redirectTo(context, navbar.class);
            }

            @Override
            public void onFailure(Call<LoginPostModel> call, Throwable t) {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
