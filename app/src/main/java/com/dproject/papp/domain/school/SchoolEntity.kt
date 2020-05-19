package com.dproject.papp.domain.school

import com.google.gson.annotations.SerializedName

class SchoolEntity(var id:Int, var name:String, @SerializedName("city_id") var cityId:Int) {
    companion object {
        fun empty() = SchoolEntity(0, "", 0)
    }
}