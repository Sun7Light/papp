package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.studentGroup.*
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class StudentGroupViewModel @Inject constructor(
    val getStudentGroupsForSchool: GetStudentGroupForSchool,
    val getStudentGroup: GetStudentGroup,
    val addStudentGroup: AddStudentGroup,
    val removeStudentGroup: RemoveStudentGroup,
    val editStudentGroup: EditStudentGroup
) : BaseViewModel() {
    var studentGroupsData: MutableLiveData<List<StudentGroupEntity>> = MutableLiveData()
    var studentGroupData: MutableLiveData<StudentGroupEntity> = MutableLiveData()
    var removeStudentGroupData: MutableLiveData<None> = MutableLiveData()
    var addStudentGroupData: MutableLiveData<StudentGroupEntity> = MutableLiveData()
    var changeStydentGroupData: MutableLiveData<StudentGroupEntity> = MutableLiveData()

    fun editStudentGroup(letter: String, formDate: String) {
        editStudentGroup(StudentGroupEntity(studentGroupData.value!!.id, studentGroupData.value!!.schoolId, letter, formDate, "")) {it.either(::handleFailure, ::handleChangeStudentGroupData)}
    }

    fun removeStudentGroup(studentGroupId: Int) {
        removeStudentGroup(studentGroupId) {it.either(::handleFailure, ::handleRemoveStudentGroupData)}
    }

    fun addStudentGroup(schoolId: Int, letter: String, formDate: String) {
        addStudentGroup(AddStudentGroup.StudentGroupParams(schoolId, letter, formDate)) {it.either(::handleFailure, ::handleAddStudentGroupData)}
    }

    fun getStudentGroup(id: Int) {
        getStudentGroup(id) {it.either(::handleFailure, ::handleStudentGroupData)}
    }

    fun getStudentGroupsForSchool(schoolId: Int) {
        getStudentGroupsForSchool(schoolId) {it.either(::handleFailure, ::handleStudentGroupsData)}
    }

    private fun handleStudentGroupsData(studentGroups: List<StudentGroupEntity>) {
        studentGroupsData.value = studentGroups
    }

    private fun handleStudentGroupData(studentGroupEntity: StudentGroupEntity) {
        studentGroupData.value = studentGroupEntity
    }

    private fun handleAddStudentGroupData(studentGroupEntity: StudentGroupEntity) {
        addStudentGroupData.value = studentGroupEntity
    }

    private fun handleRemoveStudentGroupData(none: None) {
        removeStudentGroupData.value = none
    }

    private fun handleChangeStudentGroupData(studentGroupEntity: StudentGroupEntity) {
        changeStydentGroupData.value = studentGroupEntity
    }

    override fun onCleared() {
        super.onCleared()
        getStudentGroupsForSchool.unsubscribe()
        getStudentGroup.unsubscribe()
        addStudentGroup.unsubscribe()
        removeStudentGroup.unsubscribe()
        editStudentGroup.unsubscribe()
    }
}