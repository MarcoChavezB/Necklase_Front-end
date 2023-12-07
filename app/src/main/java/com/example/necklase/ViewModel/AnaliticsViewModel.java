package com.example.necklase.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necklase.MVVM.Interactors.AnaliticsInteractor;

public class AnaliticsViewModel extends ViewModel {

    Context context;
    public AnaliticsViewModel(Context context){
        this.context = context;
    }
    private AnaliticsInteractor analiticsInteractor = new AnaliticsInteractor(context);

    private LiveData<String> info = new MutableLiveData<>();

    public LiveData<String> getInfo(String device) {
        info = analiticsInteractor.getInfoDog(device);
        return info;
    }

    public LiveData<String> getHum(String device){
        info = analiticsInteractor.getHum(device);
        return info;
    }
}

