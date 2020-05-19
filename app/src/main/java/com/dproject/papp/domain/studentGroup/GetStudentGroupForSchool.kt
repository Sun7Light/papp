package com.dproject.papp.domain.studentGroup

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetStudentGroupForSchool @Inject constructor(
    private val repository: StudentGroupRepository
) : UseCase<List<StudentGroupEntity>, Int>() {

    override suspend fun run(params: Int): Either<Failure, List<StudentGroupEntity>> {
        return repository.getStudentGroupForSchool(params)
    }
}