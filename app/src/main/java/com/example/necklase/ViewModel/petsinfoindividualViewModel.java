package com.example.necklase.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necklase.Model.Get.PetModel;

public class petsinfoindividualViewModel extends ViewModel {
    private MutableLiveData<PetModel> info = new MutableLiveData<>();
    public void setData(PetModel pt){
        info.postValue(pt);
    }
    public LiveData<PetModel> getData(){
        return info;
    }

}
