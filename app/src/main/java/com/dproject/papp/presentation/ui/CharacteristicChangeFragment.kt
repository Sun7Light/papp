package com.dproject.papp.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import com.dproject.papp.App
import com.dproject.papp.R
import com.dproject.papp.domain.characteristic.CharacteristicEntity
import com.dproject.papp.domain.characteristic.Positivity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.presentation.ui.core.BaseFragment
import com.dproject.papp.presentation.ui.core.observe
import com.dproject.papp.presentation.ui.core.onFailure
import com.dproject.papp.presentation.viewModel.CharacteristicViewModel
import kotlinx.android.synthetic.main.fragment_char_edit.*
import kotlinx.android.synthetic.main.fragment_edit_student.confirmStudButton

class CharacteristicChangeFragment: BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_char_edit

    override val titleToolbar: Int
        get() = R.string.change_characteristic_screen

    private lateinit var characteristicViewModel: CharacteristicViewModel
    private lateinit var spPositivity: AppCompatSpinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        characteristicViewModel = viewModel {
            observe(characteristicData, ::handleCharacteristicData)
            observe(changeCharacteristicData, ::handleEditCharacteristic)
            onFailure(failureData, ::handleFailure)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmStudButton.text = "Сохранить"
        confirmStudButton.setOnClickListener {
            characteristicViewModel.editCharacteristic(editCharName.text.toString(), spPositivity.selectedItem as Positivity)
        }
        spPositivity = positivitySpinner
        val positivityList = listOf<Positivity>(Positivity.positiv, Positivity.neutral, Positivity.negative)
        var adapter = ArrayAdapter(activity!!, R.layout.support_simple_spinner_dropdown_item, positivityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPositivity.adapter = adapter

        val params = activity!!.intent.getBundleExtra("args")
        val charId = params.getInt(CharacteristicChangeActivity.INTENT_EXTRA_PARAM_CHAR_ID)

        characteristicViewModel.getCharacteristic(charId)
    }

    private fun handleFailure(faulire: Failure?) {

    }

    private fun handleEditCharacteristic(characteristicEntity: CharacteristicEntity?) {
        activity?.finish()
    }

    private fun handleCharacteristicData(characteristicEntity: CharacteristicEntity?) {
        editCharName.setText(characteristicEntity?.name)

        if(characteristicEntity?.positivity == Positivity.positiv)
            spPositivity.setSelection(0)
        else if(characteristicEntity?.positivity == Positivity.neutral)
            spPositivity.setSelection(1)
        else if(characteristicEntity?.positivity == Positivity.negative)
            spPositivity.setSelection(2)
    }
}