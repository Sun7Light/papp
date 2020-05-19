package com.dproject.papp.domain.personalValueGroup

class PersonalValueGroupEntity(var id: Int, var name:String) {
    companion object {
        fun empty() = PersonalValueGroupEntity(0, "")
    }
}