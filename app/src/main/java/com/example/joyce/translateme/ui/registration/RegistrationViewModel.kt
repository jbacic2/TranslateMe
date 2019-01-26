package com.example.joyce.translateme.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joyce.translateme.data.TranslatorRepository
import com.example.joyce.translateme.data.models.RegistrationResponse
import io.reactivex.disposables.CompositeDisposable

class RegistrationViewModel(private val repository: TranslatorRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _result = MutableLiveData<RegistrationResponse>()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}