package com.myapp.wildtestapp.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.myapp.wildtestapp.R
import com.myapp.wildtestapp.viewmodel.MainViewModel
import com.myapp.wildtestapp.viewmodel.WebViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<FirebaseRemoteConfig> {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
        remoteConfig
    }
    single<DatabaseReference> {
        Firebase.database.reference
    }
}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { WebViewModel(get()) }
}