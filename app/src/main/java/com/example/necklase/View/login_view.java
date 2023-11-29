package com.example.necklase.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Extras.EmailValidator;
import com.example.necklase.Model.Post.LoginManagment;
import com.example.necklase.Model.Post.LoginPostModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModel.LoginViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login_view extends AppCompatActivity {

    ImageButton back;
    TextView signuptxt;
    Button login;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        back = findViewById(R.id.regresar);
        signuptxt = findViewById(R.id.signuptxt);
        login = findViewById(R.id.login);
        email = findViewById(R.id.emailTXT);
        password = findViewById(R.id.passwordTXT);


        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String email1 = email.getText().toString();
                String password1 = password.getText().toString();

                if(EmailValidator.isValidEmail(email1) == false){
                    Toast.makeText(login_view.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginViewModel loginViewModel = new LoginViewModel(login_view.this.getApplication());
                loginViewModel.login(email1, password1);
            }
        });



        back.setOnClickListener(v -> {
            startActivity(new Intent(login_view.this, Login_or_sign.class));
        });

        signuptxt.setOnClickListener(v -> {
            Router.redirectTo(login_view.this, register_view.class);
        });
    }
}