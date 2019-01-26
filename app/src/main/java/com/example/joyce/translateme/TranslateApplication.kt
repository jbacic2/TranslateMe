package com.example.joyce.translateme

import android.app.Application
import com.example.joyce.translateme.di.applicationModule
import com.example.joyce.translateme.di.networkModule
import org.koin.android.ext.android.startKoin

class TranslateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(applicationModule, networkModule))
    }

}