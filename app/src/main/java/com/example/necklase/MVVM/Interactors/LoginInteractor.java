package com.example.necklase.MVVM.Interactors;
import android.content.Context;
import android.widget.Toast;
import com.example.necklase.Model.Post.LoginManagment;
import com.example.necklase.Model.Post.LoginPostModel;
import com.example.necklase.Model.RetrofitApiModel;
import com.example.necklase.Router.Router;
import com.example.necklase.View.navbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginInteractor {
    private Context context;
    public LoginInteractor(Context context){
           this.context = context;
    }

    public Response login(String email, String password){

        RetrofitApiModel retro = new RetrofitApiModel(this.context);
        Retrofit retrofit = retro.provideRetrofit();
        LoginManagment loginManagment = new LoginManagment(retrofit);

        loginManagment.postData(email, password, new Callback<LoginPostModel>() {
            @Override
            public void onResponse(Call<LoginPostModel> call, Response<LoginPostModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show();

                    Router.redirectTo(context, navbar.class);
                }
            }

            @Override
            public void onFailure(Call<LoginPostModel> call, Throwable t) {

            }
        });
    }
}
