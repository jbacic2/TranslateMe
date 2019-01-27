package com.example.joyce.translateme.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.joyce.translateme.data.models.*
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TranslatorRepository(private val apiService: ApiService,
                           private val socketService: SocketService,
                           private val dataDatabase: DataDatabase) {

    fun getLocalUserInfo(): User? {
        return dataDatabase.getUserDao().getUser()
    }

    fun saveLocalUserInfo(user: User) {
        dataDatabase.getUserDao().insertUser(user)
    }

    fun updateLocalUserInfo(user: User) {
        dataDatabase.getUserDao().updateUser(user)
    }

    fun deleteLocalUserInfo(user: User) {
        dataDatabase.getUserDao().deleteUser(user)
    }

    fun registerUser(user: User): Single<RegistrationResponse> {
        return apiService.register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun connectSocket(): Flowable<WebSocket.Event> {
        return socketService.observeWebSocketEvent()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    fun sendLocationUpdate(user: User) {
        socketService.sendLocationUpdate(SocketMessage(user))
    }

    fun logon(user: User) {
        socketService.logon(SocketMessage(user, "logon"))
    }

    fun observePlan(): Flowable<UserPlan> {
        return socketService.observePlan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun observeLocationUpdates(user: User): Flowable<User> {
        return socketService.observeLocationUpdates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}