package com.dproject.papp.domain.studentGroup

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class EditStudentGroup @Inject constructor(
    private val repository: StudentGroupRepository
) : UseCase<StudentGroupEntity, StudentGroupEntity>() {

    override suspend fun run(params: StudentGroupEntity): Either<Failure, StudentGroupEntity> {
        return repository.updateStudentGroup(params)
    }
}