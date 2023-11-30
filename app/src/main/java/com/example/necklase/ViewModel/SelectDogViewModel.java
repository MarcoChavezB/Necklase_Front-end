package com.example.necklase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.necklase.MVVM.Interactors.SelectDogInteractors;
import com.example.necklase.Model.Get.DeviceUserModel;

import java.util.List;

public class SelectDogViewModel extends AndroidViewModel {
    private MutableLiveData<List<DeviceUserModel>> deviceUserListLiveData = new MutableLiveData<>();

    public SelectDogViewModel(@NonNull Application application) {
        super(application);
    }

    public void getDeviceUser(String id){
        SelectDogInteractors selectDogInteractors = new SelectDogInteractors(getApplication().getApplicationContext());
        selectDogInteractors.getDeviceUser(id, this);
    }


    public LiveData<List<DeviceUserModel>> getDeviceUserListLiveData() {
        return deviceUserListLiveData;
    }

    public void onDataReceived(List<DeviceUserModel> deviceUserList) {
        deviceUserListLiveData.postValue(deviceUserList);
    }
}
