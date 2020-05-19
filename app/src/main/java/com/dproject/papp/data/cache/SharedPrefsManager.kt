package com.dproject.papp.data.cache

import android.content.SharedPreferences
import com.dproject.papp.data.AccountType
import com.dproject.papp.data.DataAccountEntity
import com.dproject.papp.data.DataAccountMapper
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.account.AccountEntity
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_TOKEN = "account_token"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_UID = "account_uid"
        const val ACCOUNT_CLIENT = "account_client"
        const val ACCOUNT_SCHOOL_ID = "account_school_ID"
        const val ACCOUNT_STUDENT_ID = "account_student_ID"
        const val ACCOUNT_TYPE = "account_type"
    }

    fun saveAccount(account: AccountEntity): Either<Failure, None> {
        prefs.edit().apply {
            val dataAccount:DataAccountEntity = DataAccountMapper.toDataAccount(account)
            putInt(ACCOUNT_ID, dataAccount.id)
            putString(ACCOUNT_UID, dataAccount.uid)
            putString(ACCOUNT_CLIENT, dataAccount.client)
            putString(ACCOUNT_TOKEN, dataAccount.accessToken)
            putString(ACCOUNT_TYPE, dataAccount.type.name)
            putInt(ACCOUNT_SCHOOL_ID, dataAccount.schoolId)
            putInt(ACCOUNT_STUDENT_ID, dataAccount.studentId)
        }.apply()

        return Either.Right(None())
    }
//Пофиксить с нулями траблу
    fun getAccount(): Either<Failure, AccountEntity> {
        val id = prefs.getInt(ACCOUNT_ID, 0)

        if (id == 0) {
            return Either.Left(Failure.NoSavedAccountsError)
        }


        val dataAccount = DataAccountEntity(
                prefs.getInt(ACCOUNT_ID, 0),
                prefs.getString(ACCOUNT_UID, "")!!,
                prefs.getString(ACCOUNT_TOKEN, "")!!,
                prefs.getString(ACCOUNT_CLIENT, "")!!,
                AccountType.valueOf(prefs.getString(ACCOUNT_TYPE, "")!!),
                prefs.getInt(ACCOUNT_SCHOOL_ID, 0),
                prefs.getInt(ACCOUNT_STUDENT_ID, 0)
         )



        return Either.Right(DataAccountMapper.toAccountEntity(dataAccount))
    }

    fun removeAccount(): Either<Failure, None> {
        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_UID)
            remove(ACCOUNT_TOKEN)
            remove(ACCOUNT_CLIENT)
            remove(ACCOUNT_TYPE)
            remove(ACCOUNT_SCHOOL_ID)
            remove(ACCOUNT_STUDENT_ID)
        }.apply()

        return Either.Right(None())
    }
}