package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.school.*
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class SchoolViewModel @Inject constructor(
    val getSchoolForCity: GetSchoolForCity,
    val getSchool: GetSchool,
    val addSchool: AddSchool,
    val removeSchool: RemoveSchool,
    val editSchool: EditSchool
) : BaseViewModel() {
    var schoolsData: MutableLiveData<List<SchoolEntity>> = MutableLiveData()
    var schoolData: MutableLiveData<SchoolEntity> = MutableLiveData()
    var removeSchoolData: MutableLiveData<None> = MutableLiveData()
    var addSchoolData: MutableLiveData<SchoolEntity> = MutableLiveData()
    var changeSchoolData: MutableLiveData<SchoolEntity> = MutableLiveData()

    fun editSchool(schoolName: String) {
        editSchool(SchoolEntity(schoolData.value!!.id, schoolName, schoolData.value!!.cityId)) {it.either(::handleFailure, ::handleChangeSchoolData)}
    }

    fun removeSchool(schoolId: Int) {
        removeSchool(schoolId) {it.either(::handleFailure, ::handleRemoveSchoolData)}
    }

    fun addSchool(schoolName: String, cityId: Int) {
        addSchool(AddSchool.SchoolParams(schoolName, cityId)) {it.either(::handleFailure, ::handleAddSchoolData)}
    }

    fun getSchool(id: Int) {
        getSchool(id) {it.either(::handleFailure, ::handleSchoolData)}
    }

    fun getSchoolsForCity(cityId: Int) {
        getSchoolForCity(cityId) {it.either(::handleFailure, ::handleSchoolsData)}
    }

    private fun handleSchoolsData(schools: List<SchoolEntity>) {
        schoolsData.value = schools
    }

    private fun handleSchoolData(school: SchoolEntity) {
        schoolData.value = school
    }

    private fun handleAddSchoolData(school: SchoolEntity) {
        addSchoolData.value = school
    }

    private fun handleRemoveSchoolData(none: None) {
        removeSchoolData.value = none
    }

    private fun handleChangeSchoolData(school: SchoolEntity) {
        changeSchoolData.value = school
    }

    override fun onCleared() {
        super.onCleared()
        getSchoolForCity.unsubscribe()
        getSchool.unsubscribe()
        addSchool.unsubscribe()
        removeSchool.unsubscribe()
        editSchool.unsubscribe()
    }
}