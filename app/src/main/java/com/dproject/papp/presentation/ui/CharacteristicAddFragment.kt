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

class CharacteristicAddFragment: BaseFragment() {
    override val layoutId: Int = R.layout.fragment_char_edit

    override val titleToolbar: Int
    get() = R.string.create_characteristic_screen

    private lateinit var spPositivity: AppCompatSpinner
    private lateinit var characteristicViewModel: CharacteristicViewModel
    companion object {
        const val INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP_ID = "INTENT_PARAM_PERSONAL_VALUE_GROUP_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        characteristicViewModel = viewModel {
            observe(addCharacteristicData, ::handleAddCharacteristicData)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmStudButton.text = "Создать"
        val params = activity!!.intent.getBundleExtra("args")
        val personalValueGroupId = params.getInt(CharacteristicAddFragment.INTENT_EXTRA_PARAM_PERSONAL_VALUE_GROUP_ID)
        confirmStudButton.setOnClickListener { characteristicViewModel.addCharacteristic(editCharName.text.toString(), spPositivity.selectedItem as Positivity, personalValueGroupId)}
        spPositivity = positivitySpinner
        val positivityList = listOf<Positivity>(Positivity.positiv, Positivity.neutral, Positivity.negative)
        var adapter = ArrayAdapter(activity!!, R.layout.support_simple_spinner_dropdown_item, positivityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPositivity.adapter = adapter
    }


    private fun handleFailure(failure: Failure?) {

    }

    private fun handleAddCharacteristicData(characteristicEntity: CharacteristicEntity?) {
        activity?.finish()
    }
}