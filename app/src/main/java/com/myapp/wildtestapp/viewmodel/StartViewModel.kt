package com.myapp.wildtestapp.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class StartViewModel(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : ViewModel() {
    init {

    }
}