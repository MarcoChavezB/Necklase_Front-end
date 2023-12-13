package com.example.necklase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegisterPetViewModel extends AndroidViewModel {

    private MutableLiveData<String> code = new MutableLiveData<>();
    private MutableLiveData<String> genre = new MutableLiveData<>();
    public LiveData<String> getCode() {
        return code;
    }
    public LiveData<String> getGenre() {
        return genre;
    }

    public void setCode(String code) {
        this.code.postValue(code);
    }

    public void setGenre(String genre) {
        this.genre.postValue(genre);
    }

    public RegisterPetViewModel(@NonNull Application application) {
        super(application);
    }



}
