package com.dproject.papp.domain.school

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class AddSchool @Inject constructor(
    private val repository: SchoolRepository
) : UseCase<SchoolEntity, AddSchool.SchoolParams>() {

    override suspend fun run(params: AddSchool.SchoolParams): Either<Failure, SchoolEntity> {
        return repository.addSchool(SchoolEntity(0,params.name,params.cityId))
    }
    data class SchoolParams(val name: String, val cityId:Int)
}