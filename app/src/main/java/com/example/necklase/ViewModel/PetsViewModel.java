package com.example.necklase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.necklase.MVVM.Interactors.PetsInteractor;
import com.example.necklase.Model.Get.PetModel;

import java.util.List;

public class PetsViewModel extends AndroidViewModel {

    private MutableLiveData<List<PetModel>> mipet = new MutableLiveData<>();

    public PetsViewModel(@NonNull Application application) {
        super(application);
    }

    PetsInteractor pts = new PetsInteractor(getApplication().getApplicationContext());
    public void GetData(String id){
        pts.getPets(id);
        pts.lista.observe(getApplication(), new Observer<List<PetModel>>() {
            @Override
            public void onChanged(List<PetModel> petModels) {
                mipet.postValue(petModels);
            }
        });
    }



}
