package com.example.necklase.View;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.Extras.EmailValidator;
import com.example.necklase.Model.Post.RegisterManagment;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Post.RegisterPostModel;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModel.RegisterViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class register_view extends AppCompatActivity{

    private ImageView img;
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
        register = findViewById(R.id.registerBTN);
        lastName = findViewById(R.id.last_name);
        img = findViewById(R.id.circleanim);
        Animation scaleAnimation = AnimationUtils.loadAnimation(register_view.this, R.anim.light_blur_blue_slow);
        img.startAnimation(scaleAnimation);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String confirm = passwordConfirm.getText().toString();
                String lastName1 = lastName.getText().toString();

                if(EmailValidator.isValidEmail(email1) == false){
                    Toast.makeText(register_view.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password1.length() <= 8){
                    Toast.makeText(register_view.this, "contraseña muy corta", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password1 != confirm){
                    Toast.makeText(register_view.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegisterViewModel registerViewModel = new RegisterViewModel(register_view.this.getApplication());
                registerViewModel.registrar(name1, lastName1, email1, password1);

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