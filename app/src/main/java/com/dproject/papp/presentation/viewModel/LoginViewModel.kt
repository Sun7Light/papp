package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.account.Login
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val loginUseCase: Login
) : BaseViewModel() {

    var loginData: MutableLiveData<AccountEntity> = MutableLiveData()

    fun login(login:String, password:String) {
        loginUseCase(Login.LoginParams(login, password)) {it.either(::handleFailure, ::handleLogin)}
    }

    private fun handleLogin(accountEntity: AccountEntity) {
        loginData.value = accountEntity
    }

    override fun onCleared() {
        super.onCleared()
        loginUseCase.unsubscribe()
    }
}