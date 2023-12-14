package com.example.necklase.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoogleMapsViewModel extends ViewModel {

    private MutableLiveData<String> latitud = new MutableLiveData<>();
    private MutableLiveData<String> longitud = new MutableLiveData<>();
    private MutableLiveData<String> devicecode = new MutableLiveData<>();
    public LiveData<String> getLatitud() {
        return latitud;
    }

    public LiveData<String> getLongitud() {
        return longitud;
    }

    public LiveData<String> getDevicecode() {
        return devicecode;
    }

    public void setLatitud(String lat){
        this.latitud.postValue(lat);
    }

    public void setLongitud(String lon){
        this.longitud.postValue(lon);
    }

    public void setDevicecode(String lat){
        this.devicecode.postValue(lat);
    }



}
