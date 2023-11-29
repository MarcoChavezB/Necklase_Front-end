package com.example.necklase.ViewModel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.necklase.MVVM.Interactors.LoginInteractor;


public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String email, String password){
        LoginInteractor loginInteractor = new LoginInteractor(getApplication().getApplicationContext());
        loginInteractor.login(email, password);
    }

    public void checkDevices(String id){
        LoginInteractor loginInteractor = new LoginInteractor(getApplication().getApplicationContext());
        loginInteractor.checkDevices(id);
    }
}
