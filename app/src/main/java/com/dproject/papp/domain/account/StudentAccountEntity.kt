package com.dproject.papp.domain.account

class StudentAccountEntity(id:Int, uid:String, accessToken:String, client:String, var studentId:Int): AccountEntity(id,uid,accessToken,client) {
    override fun toString(): String {
        return "Школьник"
    }
}
