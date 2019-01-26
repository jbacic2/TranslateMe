package com.example.joyce.translateme.di

import com.example.joyce.translateme.data.TranslatorRepository
import org.koin.dsl.module.module

val applicationModule = module {

    single {
        TranslatorRepository(get(), get())
    }

}