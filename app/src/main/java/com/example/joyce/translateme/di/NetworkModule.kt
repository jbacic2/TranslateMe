package com.example.joyce.translateme.di

import com.example.joyce.translateme.data.ApiService
import com.example.joyce.translateme.data.SocketService
import com.google.gson.GsonBuilder
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
                .baseUrl("https://radiant-castle-70937.herokuapp.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
                .create(ApiService::class.java)
    }

    single {
        Scarlet.Builder()
                .webSocketFactory(get<OkHttpClient>().newWebSocketFactory("wss://radiant-castle-70937.herokuapp.com/"))
                .addMessageAdapterFactory(GsonMessageAdapter.Factory())
                .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
                .build()
                .create(SocketService::class.java)
    }

    single {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    single { GsonBuilder().create() }

}