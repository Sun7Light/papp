package com.dproject.papp.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import com.dproject.papp.domain.city.*
import com.dproject.papp.domain.core.None
import com.dproject.papp.presentation.viewModel.core.BaseViewModel
import javax.inject.Inject


class CityViewModel @Inject constructor(
    val getCities: GetCities,
    val getCity: GetCity,
    val addCity: AddCity,
    val removeCity: RemoveCity,
    val editCity: EditCity
) : BaseViewModel() {
    var citiesData: MutableLiveData<List<CityEntity>> = MutableLiveData()
    var cityData: MutableLiveData<CityEntity> = MutableLiveData()
    var removeCityData: MutableLiveData<None> = MutableLiveData()
    var addCityData: MutableLiveData<CityEntity> = MutableLiveData()
    var changeCityData: MutableLiveData<CityEntity> = MutableLiveData()

    fun editCity(cityName: String) {
        editCity(CityEntity(cityData.value!!.id, cityName)) {it.either(::handleFailure, ::handleChangeCityData)}
    }

    fun removeCity(cityId: Int) {
        removeCity(cityId) {it.either(::handleFailure, ::handleRemoveCityData)}
    }

    fun addCity(cityName: String) {
        addCity(AddCity.CityParams(cityName)) {it.either(::handleFailure, ::handleAddCityData)}
    }

    fun getCity(id: Int) {
        getCity(id) {it.either(::handleFailure, ::handleCityData)}
    }

    fun getCities() {
        getCities(None()) {it.either(::handleFailure, ::handleCitiesData)}
    }

    private fun handleCitiesData(cities: List<CityEntity>) {
        citiesData.value = cities
    }

    private fun handleCityData(city: CityEntity) {
        cityData.value = city
    }

    private fun handleAddCityData(city: CityEntity) {
        addCityData.value = city
    }

    private fun handleRemoveCityData(none: None) {
        removeCityData.value = none
    }

    private fun handleChangeCityData(city: CityEntity) {
        changeCityData.value = city
    }

    override fun onCleared() {
        super.onCleared()
        getCities.unsubscribe()
        getCity.unsubscribe()
        addCity.unsubscribe()
        removeCity.unsubscribe()
        editCity.unsubscribe()
    }
}