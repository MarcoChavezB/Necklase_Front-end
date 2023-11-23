package com.example.necklase.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.Extras.EmailValidator;
import com.example.necklase.Model.Post.RegisterManagment;
import com.example.necklase.Model.RetrofitApiModel;
import com.example.necklase.Model.Post.RegisterPostModel;
import com.example.necklase.Model.RetrofitInterfaces.RegisterInterface;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class register_view extends AppCompatActivity{

    private ImageButton back;
    EditText name, email, password, passwordConfirm, lastName;
    TextView signintxt;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        back = findViewById(R.id.regresar);
        signintxt = findViewById(R.id.signintxt);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        register = findViewById(R.id.register);
        lastName = findViewById(R.id.last_name);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String lastName1 = lastName.getText().toString();

                if(EmailValidator.isValidEmail(email1) == false){
                    Toast.makeText(register_view.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                RetrofitApiModel retrofitApiModel = new RetrofitApiModel();
                Retrofit retrofit = retrofitApiModel.provideRetrofit();
                RegisterManagment registerManagment = new RegisterManagment(retrofit);

                registerManagment.postData(name1, lastName1, email1, password1, new Callback<RegisterPostModel>() {
                    @Override
                    public void onResponse(Call<RegisterPostModel> call, Response<RegisterPostModel> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(register_view.this, "Register successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(register_view.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterPostModel> call, Throwable t) {
                    }
                });

            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register_view.this, Login_or_sign.class));
            }
        });
        signintxt.setOnClickListener(v -> {
            Router.redirectTo(register_view.this, login_view.class);
        });
    }


}