package com.example.joyce.translateme.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joyce.translateme.data.TranslatorRepository
import com.example.joyce.translateme.data.models.Role
import com.example.joyce.translateme.data.models.User
import com.example.joyce.translateme.data.models.UserPlan
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.mapbox.mapboxsdk.geometry.LatLng
import com.tinder.scarlet.WebSocket
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MainViewModel(private val repository: TranslatorRepository, private val fusedLocationProviderClient: FusedLocationProviderClient) : ViewModel() {

    private val disposables = CompositeDisposable()
    private var socketConnected: Boolean = false

    private val _plan = MutableLiveData<String>()
    val plan: LiveData<String> = _plan

    private val _otherUser = MutableLiveData<User>()
    val otherUser: LiveData<User> = _otherUser

    private val _currentPosition = MutableLiveData<LatLng?>()
    val currentPosition: LiveData<LatLng?> = _currentPosition

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            if (result != null) {
                val user = repository.getLocalUserInfo()!!
                val newUser = User(user.username, user.name, user.role, result.lastLocation.latitude, result.lastLocation.longitude)
                // Send update to server via socket
                repository.sendLocationUpdate(newUser)
                _currentPosition.value = LatLng(result.lastLocation.latitude, result.lastLocation.longitude)
            }
        }
    }

    fun connectSocket(newRole: Role) {
        repository.connectSocket()
                .subscribe {
                    socketConnected = when (it) {
                        is WebSocket.Event.OnConnectionOpened<*> -> true
                        else -> false
                    }

                }.addTo(disposables)

        val user = repository.getLocalUserInfo()!!
        val updatedUser = User(user.username, user.name, newRole, currentPosition.value?.latitude, currentPosition.value?.longitude)
        repository.logon(updatedUser)
        repository.updateLocalUserInfo(updatedUser)

        repository.observePlan()
                .subscribe {
                    _plan.postValue(it.path)
                    _otherUser.postValue(it.user)
                }
                .addTo(disposables)
    }

    @SuppressLint("MissingPermission")
    fun getLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(LocationRequest().setInterval(30 * 1000)
                .setFastestInterval(10 * 1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY),
                locationCallback,
                null)
    }

    fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}