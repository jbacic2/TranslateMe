package com.example.joyce.translateme.data

import com.example.joyce.translateme.data.models.RegistrationResponse
import com.example.joyce.translateme.data.models.User
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TranslatorRepository(private val apiService: ApiService, private val socketService: SocketService) {

    fun registerUser(user: User): Single<RegistrationResponse> {
        return apiService.register(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateUserRole(user: User): Single<RegistrationResponse> {
        return apiService.login(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    // TODO Return value
    fun requestHelp(user: User) {
        apiService.requestHelp(user)
    }

    fun checkForRequest(user: User) {
        apiService.checkForRequest(user)
    }

    fun sendLocationUpdate(user: User) {
        socketService.sendLocationUpdate(user)
    }

    fun observeLocationUpdates(user: User): Flowable<List<User>> {
        return socketService.observeLocationUpdates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}