package com.dproject.papp.domain.studentGroup

import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.interactor.UseCase
import javax.inject.Inject

class AddStudentGroup @Inject constructor(
    private val repository: StudentGroupRepository
) : UseCase<StudentGroupEntity, AddStudentGroup.StudentGroupParams>() {

    override suspend fun run(params: StudentGroupParams): Either<Failure, StudentGroupEntity> {
        return repository.addStudentGroup(StudentGroupEntity(0,params.schoolId,params.letter, params.formDate, "" ))
    }

    data class StudentGroupParams(val schoolId:Int, val letter: String, val formDate:String) {

    }
}


