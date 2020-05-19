package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.AccountApiService
import com.dproject.papp.data.remote.service.LoginBody
import com.dproject.papp.data.remote.service.RegistrationBody
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.account.AccountRepository
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.core.functional.onNext

class AccountRepositoryImpl(private val accountApiService: AccountApiService, private val request: Request, private val accountCache: AccountCache): AccountRepository {
    override fun register(login: String, password: String, token: String): Either<Failure, None> {
        return request.make(accountApiService.registration(RegistrationBody(login, password, token)), DataAccountEntity.empty()) { None() }
    }

    override fun login(login: String, password: String): Either<Failure, AccountEntity> {
        return request.makeLogin(accountApiService.login(LoginBody(login, password))) {
            DataAccountMapper.toAccountEntity(it)
        }
            .onNext {
                accountCache.saveAccount(it)
            }

    }

    override fun logout(): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap {
                request.make(accountApiService.logout(it.accessToken, it.client, it.uid), Any()) { None() }
            }
            .onNext { accountCache.removeAccount() }
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        return accountCache.getAccount()
            .flatMap {
                val result = request.make(accountApiService.validateToken(it.accessToken, it.client, it.uid), Any()) { None() }
                if(result is Either.Left)
                    return@flatMap Either.Left(result.a)
                else
                    return@flatMap Either.Right(it)
            }
    }
}