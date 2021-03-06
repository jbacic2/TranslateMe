package com.example.joyce.translateme.di

import android.preference.PreferenceManager
import com.example.joyce.translateme.data.DataDatabase
import com.example.joyce.translateme.data.TranslatorRepository
import com.example.joyce.translateme.ui.MainViewModel
import com.example.joyce.translateme.ui.registration.RegistrationViewModel
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {

    single {
        TranslatorRepository(get(), get(), get())
    }

    single {
        LocationServices.getFusedLocationProviderClient(androidApplication())
    }

    single {
        DataDatabase.getInstance(androidApplication())
    }

    viewModel { RegistrationViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }

}