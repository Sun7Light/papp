package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.account.GetAccount
import com.dproject.papp.domain.account.Logout
import com.dproject.papp.domain.core.None
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val logoutUseCase: Logout,
    val getAccountUseCase: GetAccount
) : BaseViewModel() {

    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()

    fun logout() {
        logoutUseCase(None()) {it.either(::handleFailure, ::handleLogout)}
    }

    fun getAccount() {
        getAccountUseCase(None()) {it.either(::handleFailure, ::handleGetAccount)}
    }

    private fun handleGetAccount(accountEntity: AccountEntity) {
        accountData.value = accountEntity
    }

    private fun handleLogout(none: None) {
        logoutData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        logoutUseCase.unsubscribe()
        getAccountUseCase.unsubscribe()
    }
}