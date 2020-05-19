package com.dproject.papp.domain.city

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface CityRepository {
    fun addCity(cityEntity: CityEntity): Either<Failure, CityEntity>
    fun getCities(): Either<Failure, List<CityEntity>>
    fun getCity(cityId:Int): Either<Failure, CityEntity>
    fun removeCity(cityId: Int): Either<Failure, None>
    fun updateCity(cityEntity: CityEntity): Either<Failure, CityEntity>
}