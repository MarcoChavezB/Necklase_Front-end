package com.example.necklase.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.necklase.Model.Get.PetsWithoutDeviceSyncModel;

import java.util.List;

public class DevicePetInfoViewModel extends ViewModel {
    private MutableLiveData<String> devicename = new MutableLiveData<>();
    private MutableLiveData<String> deviceType = new MutableLiveData<>();
    private MutableLiveData<String> petname = new MutableLiveData<>();
    private MutableLiveData<String> petid = new MutableLiveData<>();
    private MutableLiveData<String> deviceid = new MutableLiveData<>();

    public LiveData<List<PetsWithoutDeviceSyncModel>> getListapetswithout() {
        return listapetswithout;
    }
    private MutableLiveData<List<PetsWithoutDeviceSyncModel>> listapetswithout = new MutableLiveData<>();

    public void setListapetswithout(List<PetsWithoutDeviceSyncModel> lista){
        listapetswithout.postValue(lista);
    }

    public LiveData<String> getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid.postValue(petid);
    }
    public LiveData<String> getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename.postValue(devicename);
    }

    public LiveData<String> getiddevice() {
        return deviceid;
    }

    public void setiddevice(String iddevice) {
        this.deviceid.postValue(iddevice);
    }

    public LiveData<String> getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType.postValue(deviceType);
    }

    public LiveData<String> getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname.postValue(petname);
    }



}
