package com.dproject.papp.presentation.ui.core

import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.account.GetAccount
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Authenticator @Inject constructor(private val getAccountUseCase: GetAccount) {
    fun accountLogIn(onSuccess: (accountEntity:AccountEntity) -> Unit, onFailure: (Failure) -> Unit) {
        getAccountUseCase(None()) {
            it.either({ onFailure(it) }, { onSuccess(it) })
        }
    }
}