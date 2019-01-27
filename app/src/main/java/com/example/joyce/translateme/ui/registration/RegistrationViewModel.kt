package com.example.joyce.translateme.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joyce.translateme.data.TranslatorRepository
import com.example.joyce.translateme.data.models.RegistrationResponse
import com.example.joyce.translateme.data.models.User
import io.reactivex.disposables.CompositeDisposable

class RegistrationViewModel(private val repository: TranslatorRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var username: String = ""
    private var name: String = ""

    private val _result = MutableLiveData<RegistrationResponse>()

    fun storeUserInfo(username: String, name: String) {
        this.username = username
        this.name = name
    }

    fun saveUser() {
        repository.saveLocalUserInfo(User(username, name))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}