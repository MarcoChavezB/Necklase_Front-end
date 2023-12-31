package com.example.necklase.MVVM.Interactors;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Post.RegisterManagment;
import com.example.necklase.Model.Post.RegisterPostModel;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.View.login_view;
import com.example.necklase.View.register_view;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterInteractor {
    private Context context;

    public RegisterInteractor(Context context){
        this.context = context;
    }

    public void registrar(String name, String lastName, String email, String password){
        RetrofitApiModel retro = new RetrofitApiModel(this.context);
        Retrofit retrofit = retro.provideRetrofit();
        RegisterManagment registerManagment = new RegisterManagment(retrofit);

        registerManagment.postData(name, lastName, email, password, new Callback<RegisterPostModel>() {
            @Override
            public void onResponse(Call<RegisterPostModel> call, Response<RegisterPostModel> response) {
                if(response.isSuccessful()){
                    SharedPreferences.Editor editor = context.getSharedPreferences("personal", Context.MODE_PRIVATE).edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();
                    Toast.makeText(context, "Se envio un correo de verificacion", Toast.LENGTH_SHORT).show();
                    Router.redirectTo(context, login_view.class);
                }else{
                    Toast.makeText(context, "El correo ya existe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterPostModel> call, Throwable t) {
                Toast.makeText(context, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

}