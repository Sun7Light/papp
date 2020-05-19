package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.CharacteristicApiService
import com.dproject.papp.data.remote.service.CharacteristicParams
import com.dproject.papp.domain.characteristic.CharacteristicEntity
import com.dproject.papp.domain.characteristic.CharacteristicRepository
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.core.functional.map

class CharacteristicRepositoryImpl(private val characteristicApiService: CharacteristicApiService, private val request: Request, private val accountCache: AccountCache)
    : CharacteristicRepository {

    override fun addCharacteristic(characteristicEntity: CharacteristicEntity): Either<Failure, CharacteristicEntity> {
        var dataCharacteristicEntity = DataCharacteristricMapper.toDataCharacteristicEntity(characteristicEntity)
        return accountCache.getAccount()
            .flatMap { request.make(characteristicApiService.createCharacteristic(it.accessToken, it.client, it.uid, CharacteristicParams(dataCharacteristicEntity.name, dataCharacteristicEntity.positivity, dataCharacteristicEntity.personalValueGroupId)), DataCharacteristicEntity.empty()) {DataCharacteristricMapper.toCharacteristicEntity(it)} }
    }

    override fun getCharacteristicForPersonalValueGroup(personalValueGroupId: Int): Either<Failure, List<CharacteristicEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(characteristicApiService.getCharacteristics(it.accessToken, it.client, it.uid), emptyList<DataCharacteristicEntity>()) {it} }
            .map {it.filter { dataCharacteristicEntity -> dataCharacteristicEntity.personalValueGroupId == personalValueGroupId }}
            .map {it.map { dataEntity -> DataCharacteristricMapper.toCharacteristicEntity(dataEntity) } }
    }

    override fun getCharacteristics(): Either<Failure, List<CharacteristicEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(characteristicApiService.getCharacteristics(it.accessToken, it.client, it.uid), emptyList<DataCharacteristicEntity>()) {it} }
            .map { it.map { dataEntity -> DataCharacteristricMapper.toCharacteristicEntity(dataEntity) } }
    }

    override fun getCharacteristic(characteristicEntityId: Int): Either<Failure, CharacteristicEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(characteristicApiService.getCharacteristic(it.accessToken, it.client, it.uid, characteristicEntityId), DataCharacteristicEntity.empty()) {DataCharacteristricMapper.toCharacteristicEntity (it)} }
    }

    override fun removeCharacteristic(characteristicEntityId: Int): Either<Failure, None> {
       return  accountCache.getAccount()
            .flatMap { request.make(characteristicApiService.deleteCharacteristic(it.accessToken, it.client, it.uid, characteristicEntityId), CharacteristicEntity.empty()) {None()} }
    }

    override fun updateCharacteristic(characteristicEntity: CharacteristicEntity): Either<Failure, CharacteristicEntity> {
        var dataCharacteristicEntity = DataCharacteristricMapper.toDataCharacteristicEntity(characteristicEntity)
        return accountCache.getAccount()
            .flatMap { request.make(characteristicApiService.updateCharacteristic(it.accessToken, it.client, it.uid, dataCharacteristicEntity.id, CharacteristicParams(dataCharacteristicEntity.name, dataCharacteristicEntity.positivity, dataCharacteristicEntity.personalValueGroupId)), DataCharacteristicEntity.empty()) {DataCharacteristricMapper.toCharacteristicEntity(it)} }

    }
}