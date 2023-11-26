package com.example.necklase.MVVM.Factorys;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.necklase.MVVM.ViewModels.DevicesConfigViewModel;

import androidx.lifecycle.ViewModel;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final String userId;

    public MyViewModelFactory(Application application, String userId) {
        this.application = application;
        this.userId = userId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DevicesConfigViewModel.class)) {
            return (T) new DevicesConfigViewModel(application, userId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
