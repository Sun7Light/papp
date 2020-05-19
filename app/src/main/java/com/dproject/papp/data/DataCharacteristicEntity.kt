package com.dproject.papp.data

import com.google.gson.annotations.SerializedName

class DataCharacteristicEntity(var id: Int, var name: String, var positivity: Int, @SerializedName("personal_value_group_id") var personalValueGroupId: Int) {
    companion object {
        fun empty() = DataCharacteristicEntity(0,"",0,0)
    }
}