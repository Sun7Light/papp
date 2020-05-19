package com.dproject.papp.domain.personalValueGroup

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either

interface PersonalValueGroupRepository {
    fun getPersonalValueGroups(): Either<Failure, List<PersonalValueGroupEntity>>
    fun getPersonalValueGroup(personalValueGroupId: Int): Either<Failure, PersonalValueGroupEntity>
}