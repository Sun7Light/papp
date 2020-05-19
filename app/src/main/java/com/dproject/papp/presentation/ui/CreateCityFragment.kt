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

class CreateCityFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_create_city
    override val titleToolbar: Int = R.string.create_cities_screen

    private lateinit var cityViewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        cityViewModel = viewModel {
            observe(addCityData, ::handleAddCityData)
            onFailure(failureData, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createCityButton.setOnClickListener { cityViewModel.addCity(editCityText.text.toString()) }
    }


    private fun handleFailure(failure: Failure?) {

    }

    private fun handleAddCityData(cityEntity: CityEntity?) {
        activity?.finish()
    }
}