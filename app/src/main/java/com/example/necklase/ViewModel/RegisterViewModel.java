package com.example.necklase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.necklase.MVVM.Interactors.RegisterInteractor;

public class RegisterViewModel extends AndroidViewModel {

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void registrar(String name, String lastName, String email, String password){
        RegisterInteractor registerInteractor = new RegisterInteractor(getApplication().getApplicationContext());
        registerInteractor.registrar(name, lastName, email, password);
    }
}
