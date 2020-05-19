package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.account.Register
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    val registerUseCase: Register
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()

    fun register(login:String, password:String, token:String) {
        registerUseCase(Register.RegisterParams(login, password, token)) {it.either(::handleFailure, ::handleRegister)}
    }

    private fun handleRegister(none: None) {
        registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
    }
}