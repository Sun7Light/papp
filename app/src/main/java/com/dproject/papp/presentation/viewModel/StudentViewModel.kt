package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.student.*
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class StudentViewModel @Inject constructor(
    val getStudentForStudentGroup: GetStudentForStudentGroup,
    val getStudent: GetStudent,
    val addStudent: AddStudent,
    val removeStudent: RemoveStudent,
    val editStudent: EditStudent
) : BaseViewModel() {
    var studentsData: MutableLiveData<List<StudentEntity>> = MutableLiveData()
    var studentData: MutableLiveData<StudentEntity> = MutableLiveData()
    var removeStudentData: MutableLiveData<None> = MutableLiveData()
    var addStudentData: MutableLiveData<StudentEntity> = MutableLiveData()
    var changeStydentData: MutableLiveData<StudentEntity> = MutableLiveData()

    fun editStudent(name: String, sex:Sex) {
        editStudent(StudentEntity(studentData.value!!.id, studentData.value!!.studentGroupId, name, sex, "")) {it.either(::handleFailure, ::handleChangeStudentData)}
    }

    fun removeStudent(studentId: Int) {
        removeStudent(studentId) {it.either(::handleFailure, ::handleRemoveStudentData)}
    }

    fun addStudent(studentGroupId: Int, name:String, sex:Sex) {
        addStudent(AddStudent.StudentParams(studentGroupId,name,sex)) {it.either(::handleFailure, ::handleAddStudentData)}
    }

    fun getStudent(id: Int) {
        getStudent(id) {it.either(::handleFailure, ::handleStudentData)}
    }

    fun getStudentForStudentGroup(studentGroupId: Int) {
        getStudentForStudentGroup(studentGroupId) {it.either(::handleFailure, ::handleStudentData)}
    }

    private fun handleStudentData(students: List<StudentEntity>) {
        studentsData.value = students
    }

    private fun handleStudentData(studentEntity: StudentEntity) {
        studentData.value = studentEntity
    }

    private fun handleAddStudentData(studentEntity: StudentEntity) {
        addStudentData.value = studentEntity
    }

    private fun handleRemoveStudentData(none: None) {
        removeStudentData.value = none
    }

    private fun handleChangeStudentData(studentEntity: StudentEntity) {
        changeStydentData.value = studentEntity
    }

    override fun onCleared() {
        super.onCleared()
        getStudentForStudentGroup.unsubscribe()
        getStudent.unsubscribe()
        addStudent.unsubscribe()
        removeStudent.unsubscribe()
        editStudent.unsubscribe()
    }
}