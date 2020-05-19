package com.dproject.papp.data.cache

import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountCacheImpl @Inject constructor(private val sharedPrefsManager: SharedPrefsManager): AccountCache {
    override fun saveAccount(accountEntity: AccountEntity): Either<Failure, None> {
        return sharedPrefsManager.saveAccount(accountEntity)
    }

    override fun getAccount(): Either<Failure, AccountEntity> {
        return sharedPrefsManager.getAccount()
    }

    override fun removeAccount(): Either<Failure, None> {
        return sharedPrefsManager.removeAccount()
    }
}