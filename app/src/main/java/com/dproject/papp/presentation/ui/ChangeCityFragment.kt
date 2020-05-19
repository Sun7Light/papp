package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.city.CityEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.CityViewModel
import kotlinx.android.synthetic.main.fragment_create_city.*

class ChangeCityFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_create_city
    override val titleToolbar: Int
        get() = R.string.change_city_screen

    private lateinit var cityViewModel:CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        cityViewModel = viewModel {
            observe(cityData, ::handleCityData)
            observe(changeCityData, ::handleEditCity)
            onFailure(failureData, ::handleFailure)
        }
        val params = activity!!.intent.getBundleExtra("args")
        val cityId = params.getInt(ChangeCityActivity.INTENT_EXTRA_PARAM_CITY_ID)

        cityViewModel.getCity(cityId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCityButton.text = "Сохранить"
        createCityButton.setOnClickListener {
            cityViewModel.editCity(editCityText.text.toString())
        }
    }

    private fun handleFailure(faulire: Failure?) {

    }

    private fun handleEditCity(cityEntity: CityEntity?) {
        activity?.finish()
    }

    private fun handleCityData(cityEntity: CityEntity?) {
        editCityText.setText(cityEntity?.name)
    }
}