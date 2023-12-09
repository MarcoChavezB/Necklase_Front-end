package com.example.necklase.MVVM.Interactors;
import static android.content.Context.MODE_PRIVATE;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.CheckDevicesManagment;
import com.example.necklase.Model.Post.CheckDevicesPostModel;
import com.example.necklase.Model.Post.LoginManagment;
import com.example.necklase.Model.Post.LoginPostModel;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.Router.Router;
import com.example.necklase.View.AnimationSplash;
import com.example.necklase.View.MainActivity;
import com.example.necklase.View.activity_bienvenida;
import com.example.necklase.View.navbar;
import com.example.necklase.View.selectDevice;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginInteractor {
    private Context context;
    private String id;
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

                SharedPreferences.Editor editorRM = context.getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                editorRM.remove("token");
                editorRM.apply();

                ViewModelTokenIns.clearToken(context);
                if(response.isSuccessful()){
                    String activate = response.body().getActivate();
                    if(activate.equals("1")){
                        SharedPreferences.Editor editor = context.getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                        editor.putString("token", response.body().getToken());
                        editor.apply();

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);

                    }else{
                        Toast.makeText(context, "Your account is not activated", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginPostModel> call, Throwable t) {
                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
