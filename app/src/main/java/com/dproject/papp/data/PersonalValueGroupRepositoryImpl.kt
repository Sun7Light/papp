package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.PersonalValueGroupApiService
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupEntity
import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupRepository

class PersonalValueGroupRepositoryImpl(private val personalValueGroupApiService: PersonalValueGroupApiService, private val request: Request, private val accountCache: AccountCache)
    :PersonalValueGroupRepository {

    override fun getPersonalValueGroups(): Either<Failure, List<PersonalValueGroupEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(personalValueGroupApiService.getPersonalValueGroups(it.accessToken, it.client, it.uid), emptyList<PersonalValueGroupEntity>()) {it} }
    }

    override fun getPersonalValueGroup(personalValueGroupId: Int): Either<Failure, PersonalValueGroupEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(personalValueGroupApiService.getPersonalValueGroup(it.accessToken, it.client, it.uid, personalValueGroupId), PersonalValueGroupEntity.empty()) {it} }
    }
}