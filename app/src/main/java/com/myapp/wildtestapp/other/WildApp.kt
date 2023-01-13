package com.myapp.wildtestapp.other

import android.app.Application
import com.myapp.wildtestapp.di.dataModule
import com.myapp.wildtestapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WildApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WildApp)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}