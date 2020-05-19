package com.dproject.papp.data.cache

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface AccountCache {
    fun saveAccount(accountEntity: AccountEntity): Either<Failure, None>
    fun getAccount(): Either<Failure, AccountEntity>
    fun removeAccount(): Either<Failure, None>
}