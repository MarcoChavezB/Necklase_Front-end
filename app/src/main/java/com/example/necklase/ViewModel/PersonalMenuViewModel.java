package com.example.necklase.ViewModel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.MVVM.Interactors.PersonalMenuInteractor;

public class PersonalMenuViewModel extends AndroidViewModel {

    private MutableLiveData<String> namePerson = new MutableLiveData<>();
    private MutableLiveData<String> emailPerson = new MutableLiveData<>();
    public LiveData<String> getNamePerson() {
        return namePerson;
    }
    public LiveData<String> getEmailPerson() {
        return emailPerson;
    }
    public PersonalMenuViewModel(@NonNull Application application) {
        super(application);
    }

    public void setinfoData(String id){
        PersonalMenuInteractor personalMenuInteractor = new PersonalMenuInteractor(getApplication().getApplicationContext());
        personalMenuInteractor.getInfoData(id);
        namePerson.setValue(personalMenuInteractor.namePerson);
        emailPerson.setValue(personalMenuInteractor.emailPerson);
        Toast.makeText(getApplication().getApplicationContext(), "Datos cargados" + emailPerson.getValue() + namePerson.getValue() , Toast.LENGTH_SHORT).show();
    }
}
