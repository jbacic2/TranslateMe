package com.example.joyce.translateme.ui

import androidx.lifecycle.ViewModel
import com.example.joyce.translateme.data.TranslatorRepository
import com.example.joyce.translateme.data.models.User
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val repository: TranslatorRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getLocalUserInfo(): User? = repository.getLocalUserInfo()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}