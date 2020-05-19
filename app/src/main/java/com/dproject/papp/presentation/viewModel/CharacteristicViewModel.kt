package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.characteristic.*
import com.dproject.papp.domain.core.None
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject

class CharacteristicViewModel @Inject constructor(
    val getCharacteristicForPersonalValueGroup: GetCharacteristicForPersonalValueGroup,
    val getCharacteristic: GetCharacteristic,
    val addCharacteristic: AddCharacteristic,
    val removeCharacteristic: RemoveCharacteristic,
    val editCharacteristic: EditCharacteristic
) : BaseViewModel() {
    var characteristicsData: MutableLiveData<List<CharacteristicEntity>> = MutableLiveData()
    var characteristicData: MutableLiveData<CharacteristicEntity> = MutableLiveData()
    var removeCharacteristicData: MutableLiveData<None> = MutableLiveData()
    var addCharacteristicData: MutableLiveData<CharacteristicEntity> = MutableLiveData()
    var changeCharacteristicData: MutableLiveData<CharacteristicEntity> = MutableLiveData()

    fun editCharacteristic(characteristicName: String, positivity: Positivity) {
        editCharacteristic(CharacteristicEntity(characteristicData.value!!.id, characteristicName, positivity, characteristicData.value!!.personalValueGroupId)) {it.either(::handleFailure, ::handleChangeCharacteristicData)}
    }

    fun removeCharacteristic(characteristicId: Int) {
        removeCharacteristic(characteristicId) {it.either(::handleFailure, ::handleRemoveCharacteristicData)}
    }

    fun addCharacteristic(characteristicName: String, positivity: Positivity, personalValueGroupId: Int) {
        addCharacteristic(AddCharacteristic.CharacteristicParams(characteristicName, positivity, personalValueGroupId)) {it.either(::handleFailure, ::handleAddCharacteristicData)}
    }

    fun getCharacteristic(id: Int) {
        getCharacteristic(id) {it.either(::handleFailure, ::handleCharacteristicData)}
    }

    fun getCharacteristicForPersonalValueGroup(personalValueGroupId: Int) {
        getCharacteristicForPersonalValueGroup(personalValueGroupId) {it.either(::handleFailure, ::handleCharacteristicsData)}
    }

    private fun handleCharacteristicsData(characteristics: List<CharacteristicEntity>) {
        characteristicsData.value = characteristics
    }

    private fun handleCharacteristicData(characteristic: CharacteristicEntity) {
        characteristicData.value = characteristic
    }

    private fun handleAddCharacteristicData(characteristic: CharacteristicEntity) {
        addCharacteristicData.value = characteristic
    }

    private fun handleRemoveCharacteristicData(none: None) {
        removeCharacteristicData.value = none
    }

    private fun handleChangeCharacteristicData(characteristic: CharacteristicEntity) {
        changeCharacteristicData.value = characteristic
    }

    override fun onCleared() {
        super.onCleared()
        getCharacteristicForPersonalValueGroup.unsubscribe()
        getCharacteristic.unsubscribe()
        addCharacteristic.unsubscribe()
        removeCharacteristic.unsubscribe()
        editCharacteristic.unsubscribe()
    }
}