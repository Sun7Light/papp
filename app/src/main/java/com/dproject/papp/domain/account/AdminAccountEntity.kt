package com.dproject.papp.domain.account

class AdminAccountEntity(id:Int, uid:String, accessToken:String, client:String): AccountEntity(id,uid,accessToken,client) {
    override fun toString(): String {
        return "Администратор"
    }
}