package com.dproject.papp.domain.characteristic

class CharacteristicEntity(var id: Int, var name: String, var positivity: Positivity, var personalValueGroupId: Int) {
    companion object {
        fun empty() = CharacteristicEntity(0,"",Positivity.negative,0)
    }
}

enum class Positivity(val info: String) {
    positiv ("Положительная") {
        override fun toString(): String {
            return positiv.info
        }
    }, neutral ("Нейтральная") {
        override fun toString(): String {
            return neutral.info
        }
    }, negative ("Отрицательная") {
        override fun toString(): String {
            return negative.info
        }
    };

    abstract override fun toString(): String
}
