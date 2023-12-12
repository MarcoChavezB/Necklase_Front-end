package com.example.necklase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class mapsViewModel extends AndroidViewModel {

    private MutableLiveData<List<String>> cordenadas;

    public mapsViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<String>> getData(){
        return cordenadas;
    }
}
