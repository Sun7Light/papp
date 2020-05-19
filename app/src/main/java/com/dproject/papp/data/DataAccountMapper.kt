package com.dproject.papp.data

import com.dproject.papp.domain.account.*

object DataAccountMapper {
    fun toAccountEntity(dataAccountEntity: DataAccountEntity): AccountEntity {
        return when (dataAccountEntity.type) {
            AccountType.Admin -> AdminAccountEntity(dataAccountEntity.id, dataAccountEntity.uid, dataAccountEntity.accessToken, dataAccountEntity.client)
            AccountType.Psychologist -> PsychologistAccountEntity(dataAccountEntity.id, dataAccountEntity.uid, dataAccountEntity.accessToken, dataAccountEntity.client)
            AccountType.StudentUser -> StudentAccountEntity(dataAccountEntity.id, dataAccountEntity.uid, dataAccountEntity.accessToken, dataAccountEntity.client, dataAccountEntity.studentId)
            AccountType.SchoolPsychologist -> SchoolPsychologistAccountEntity(dataAccountEntity.id, dataAccountEntity.uid, dataAccountEntity.accessToken, dataAccountEntity.client,dataAccountEntity.schoolId)
            AccountType.SchoolAdmin -> SchoolAdminAccountEntity(dataAccountEntity.id, dataAccountEntity.uid, dataAccountEntity.accessToken, dataAccountEntity.client,dataAccountEntity.schoolId)
        }
    }

    fun toDataAccount(accountEntity: AccountEntity): DataAccountEntity {
        var accountType:AccountType = AccountType.Admin
        var schoolId: Int = 0
        var studentId: Int = 0

        if(accountEntity is PsychologistAccountEntity)
            accountType = AccountType.Psychologist
        else if(accountEntity is StudentAccountEntity) {
            accountType = AccountType.StudentUser
            studentId = accountEntity.studentId
        }
        else if(accountEntity is SchoolWorkerAccountEntity) {
            schoolId = accountEntity.schoolId
            if(accountEntity is SchoolPsychologistAccountEntity) {
                accountType = AccountType.SchoolPsychologist
            }
            else if(accountEntity is SchoolAdminAccountEntity) {
                accountType = AccountType.SchoolAdmin
            }
        }

        return DataAccountEntity(accountEntity.id,accountEntity.uid,accountEntity.accessToken,accountEntity.client,accountType,schoolId,studentId)
    }
}