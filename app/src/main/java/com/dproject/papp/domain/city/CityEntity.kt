package com.dproject.papp.domain.city



class CityEntity(var id:Int, var name:String) {
    companion object {
        fun empty() = CityEntity(0,"")
    }
}