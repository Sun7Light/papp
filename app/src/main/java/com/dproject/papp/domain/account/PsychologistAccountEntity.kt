package com.dproject.papp.domain.account

class PsychologistAccountEntity(id:Int, uid:String, accessToken:String, client:String): AccountEntity(id,uid,accessToken,client) {
    override fun toString(): String {
        return "Психолог"
    }
}