package com.example.necklase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.necklase.MVVM.Interactors.PetsInteractor;
import com.example.necklase.Model.Get.PetModel;

import java.util.List;

public class PetsViewModel extends ViewModel {

    private MutableLiveData<List<PetModel>> mipet = new MutableLiveData<>();
    public void PostData(List<PetModel> pets){
        mipet.postValue(pets);
    }

    public LiveData<List<PetModel>> getLiveData(){
        return mipet;
    }
}
