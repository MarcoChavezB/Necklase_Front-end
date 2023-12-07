package com.example.necklase.ViewModel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necklase.MVVM.Interactors.PersonalMenuInteractor;

import java.util.List;

public class PersonalMenuViewModel extends ViewModel {
    Context context;

    private PersonalMenuInteractor interactor = new PersonalMenuInteractor(context);
    public PersonalMenuViewModel(Context context){
        this.context = context;
    }
    private LiveData<List<String>> info = new MutableLiveData<>();


    public LiveData<List<String>> getInfo(String id){
        info = interactor.getInfoData(id);
        return info;
    }
}
