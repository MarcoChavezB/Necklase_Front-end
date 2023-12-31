package com.example.necklase.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necklase.MVVM.Interactors.HomeInteractor;

import java.util.List;

public class HomeViewModel extends ViewModel {
    Context context;
    private LiveData<String> info = new MutableLiveData<>();
    private LiveData<List<String>> clime = new MutableLiveData<>();
    HomeInteractor interactor = new HomeInteractor(context);

    public void HomeViewModel(Context context){
        this.context = context;
    }

    public LiveData<String> getHum(String device){
        info = interactor.getHum(device);
        return info;
    }

    public LiveData<String> getDoginfo(String id){
        info = interactor.getInfoDog(id);
        return info;
    }

    public LiveData<List<String>> getClima(String coords){
        clime = interactor.getClima(coords);
        return clime;
    }

}
