package com.dproject.papp.domain.school

import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface SchoolRepository {
    fun addSchool(schoolEntity: SchoolEntity): Either<Failure, SchoolEntity>
    fun getSchoolForCity(cityId: Int): Either<Failure, List<SchoolEntity>>
    fun getSchools(): Either<Failure, List<SchoolEntity>>
    fun getSchool(schoolId: Int): Either<Failure, SchoolEntity>
    fun removeSchool(schoolId: Int): Either<Failure, None>
    fun updateSchool(schoolEntity: SchoolEntity): Either<Failure, SchoolEntity>
}