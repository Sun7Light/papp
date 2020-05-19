package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.personalValueGroup.GetPersonalValueGroup
import com.dproject.papp.domain.personalValueGroup.GetPersonalValueGroups
import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupEntity
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class PersonalValueGroupViewModel @Inject constructor(
    val getPersonalValueGroups: GetPersonalValueGroups,
    val getPersonalValueGroup: GetPersonalValueGroup
) : BaseViewModel() {
    var personalValueGroupsData: MutableLiveData<List<PersonalValueGroupEntity>> = MutableLiveData()
    var personalValueGroupData: MutableLiveData<PersonalValueGroupEntity> = MutableLiveData()


    fun getPersonValueGroup(valueGroupId: Int) {
        getPersonalValueGroup(valueGroupId) {it.either(::handleFailure, ::handlePersonalValueGroupData)}
    }

    fun getPersonalValueGroups() {
        getPersonalValueGroups(None()) {it.either(::handleFailure, ::handlePersonalValueGroupsData)}
    }

    private fun handlePersonalValueGroupsData(personalValueGroups: List<PersonalValueGroupEntity>) {
        personalValueGroupsData.value = personalValueGroups
    }

    private fun handlePersonalValueGroupData(personalValueGroupEntity: PersonalValueGroupEntity) {
        personalValueGroupData.value = personalValueGroupEntity
    }

    override fun onCleared() {
        super.onCleared()
        getPersonalValueGroups.unsubscribe()
        getPersonalValueGroup.unsubscribe()
    }
}