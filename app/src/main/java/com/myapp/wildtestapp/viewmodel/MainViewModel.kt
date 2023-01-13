package com.myapp.wildtestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.myapp.wildtestapp.model.ReceivingData
import com.myapp.wildtestapp.other.GAME_PASS
import com.myapp.wildtestapp.other.LINK

class MainViewModel(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : ViewModel() {

    private val data = MutableLiveData<ReceivingData>()

    init {
        val receivingData = ReceivingData(
            firebaseRemoteConfig.getBoolean(GAME_PASS),
            firebaseRemoteConfig.getString(LINK)
        )
        data.value = receivingData
    }

    fun getData(): LiveData<ReceivingData> = data

}