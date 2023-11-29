package com.example.necklase.MVVM.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.necklase.MVVM.Interactors.DeviceConfigInteractor;
import com.example.necklase.MVVM.Resource.Resource;
import com.example.necklase.Model.Get.Device;

import java.util.List;

public class DevicesConfigViewModel extends AndroidViewModel {
    private LiveData<Resource<List<Device>>> myDataList;
    private DeviceConfigInteractor repository;
    private String userId;

    public DevicesConfigViewModel(@NonNull Application application, String userId) {
        super(application);
        this.userId = userId;
        repository = new DeviceConfigInteractor(userId);
        myDataList = repository.getMyData(getApplication().getApplicationContext());
    }

    public LiveData<Resource<List<Device>>> getMyDataList() {
        return myDataList;
    }
}
