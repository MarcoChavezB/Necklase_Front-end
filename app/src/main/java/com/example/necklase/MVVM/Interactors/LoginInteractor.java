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
                    SharedPreferences.Editor editor = context.getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                    editor.putString("token", response.body().getToken());
                    editor.apply();

                    ViewModelTokenIns.getinstance();
                    ViewModelTokenIns.settoken(context);

                    Router.redirectTo(context, navbar.class);

                    Intent intent = new Intent(context, activity_bienvenida.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

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



    public void checkDevices(String id){
        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        CheckDevicesManagment checkDevicesManagment = new CheckDevicesManagment(retrofit);

        checkDevicesManagment.getData(id, new Callback<CheckDevicesPostModel>() {
            @Override
            public void onResponse(Call<CheckDevicesPostModel> call, Response<CheckDevicesPostModel> response) {
                if(response.isSuccessful()){
                    SharedPreferences.Editor editor = context.getSharedPreferences("Personal", MODE_PRIVATE).edit();
                    editor.putString("nDispositivos", response.body().getNumero());
                    editor.apply();

                    SharedPreferences dispositivos = context.getSharedPreferences("Personal", MODE_PRIVATE);
                    String nDispositivos = dispositivos.getString("nDispositivos", null);

                }else{
                    Toast.makeText(context, "Error al ingresar dispositivos" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CheckDevicesPostModel> call, Throwable t) {

            }
        });
    }
}
