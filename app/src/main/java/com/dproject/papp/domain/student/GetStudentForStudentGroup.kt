package com.dproject.papp.domain.student

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class GetStudentForStudentGroup @Inject constructor(
    private val repository: StudentRepository
) : UseCase<List<StudentEntity>, Int>() {

    override suspend fun run(params: Int): Either<Failure, List<StudentEntity>> {
        return repository.getStudentForStudentGroup(params)
    }
}