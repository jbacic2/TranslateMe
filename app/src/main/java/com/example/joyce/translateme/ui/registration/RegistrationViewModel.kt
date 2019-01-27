package com.example.joyce.translateme.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joyce.translateme.data.TranslatorRepository
import com.example.joyce.translateme.data.models.RegistrationResponse
import com.example.joyce.translateme.data.models.User
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RegistrationViewModel(private val repository: TranslatorRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var username: String = ""
    private var name: String = ""

    private val _result = MutableLiveData<RegistrationResponse>()
    val result: LiveData<RegistrationResponse> = _result

    fun storeUserInfo(username: String, name: String) {
        this.username = username
        this.name = name
    }

    fun saveUser() {
        val user = User(username, name)
        repository.saveLocalUserInfo(user)
        repository.registerUser(user)
                .subscribe({
                    _result.postValue(it)
                }, {
                    _result.postValue(RegistrationResponse(false))
                })
                .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}