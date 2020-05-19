package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.CityApiService
import com.dproject.papp.data.remote.service.CityParams
import com.dproject.papp.domain.city.CityEntity
import com.dproject.papp.domain.city.CityRepository
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap

class CityRepositoryImpl(private val cityApiService: CityApiService, private val request: Request, private val accountCache: AccountCache): CityRepository {
    override fun addCity(cityEntity: CityEntity): Either<Failure, CityEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(cityApiService.createCity(it.accessToken, it.client, it.uid, CityParams(cityEntity.name)), CityEntity.empty()) {it} }
    }

    override fun getCities(): Either<Failure, List<CityEntity>> {
        return accountCache.getAccount()
            .flatMap {request.make(cityApiService.getCities(it.accessToken, it.client, it.uid), emptyList<CityEntity>()) {it} }
    }

    override fun getCity(cityId: Int): Either<Failure, CityEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(cityApiService.getCity(it.accessToken, it.client, it.uid, cityId), CityEntity.empty()) {it} }
    }

    override fun removeCity(cityId: Int): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { request.make(cityApiService.deleteCity(it.accessToken,it.client,it.uid,cityId), CityEntity.empty()) { None() } }
    }

    override fun updateCity(cityEntity: CityEntity): Either<Failure, CityEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(cityApiService.updateCity(it.accessToken,it.client,it.uid,cityEntity.id, CityParams(cityEntity.name)), CityEntity.empty(), {it}) }
    }
}