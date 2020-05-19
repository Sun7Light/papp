package com.dproject.papp.domain.student

import com.google.gson.annotations.SerializedName

class StudentEntity(var id: Int, @SerializedName("student_group_id") var studentGroupId: Int, var name: String,var sex: Sex,var token:String) {
    companion object {
        fun empty() = StudentEntity(0,0,"",Sex.male,"")
    }
}

enum class Sex(val sexName:String) {
    male ("Мужской") {
        override fun toString(): String {
            return male.sexName
        }
    }, female ("Женский") {
        override fun toString(): String {
            return female.sexName
        }
    };

    abstract override fun toString(): String
}