package com.dproject.papp.domain.studentGroup

import com.google.gson.annotations.SerializedName

class StudentGroupEntity(var id: Int, @SerializedName("school_id") var schoolId: Int, var letter:String, @SerializedName("form_date") var formDate:String, var name:String) {
    companion object {
        fun empty() = StudentGroupEntity(0, 0, "", "", "")
    }
}