package com.dproject.papp.data

import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.SchoolApiService
import com.dproject.papp.data.remote.service.SchoolParams
import com.dproject.papp.domain.core.None
import com.dproject.papp.domain.core.exception.Failure
import com.dproject.papp.domain.core.functional.Either
import com.dproject.papp.domain.core.functional.flatMap
import com.dproject.papp.domain.school.SchoolEntity
import com.dproject.papp.domain.school.SchoolRepository

class SchoolRepositoryImpl(private val schoolApiService: SchoolApiService, private val request: Request, private val accountCache: AccountCache): SchoolRepository {
    override fun addSchool(schoolEntity: SchoolEntity): Either<Failure, SchoolEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(schoolApiService.createSchool(it.accessToken, it.client, it.uid, SchoolParams(schoolEntity.name, schoolEntity.cityId)), SchoolEntity.empty()) {it} }
    }

    override fun getSchoolForCity(schoolId: Int): Either<Failure, List<SchoolEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(schoolApiService.getSchoolsForCity(it.accessToken, it.client, it.uid, schoolId), emptyList()) {it} }
    }

    override fun getSchools(): Either<Failure, List<SchoolEntity>> {
        return accountCache.getAccount()
            .flatMap { request.make(schoolApiService.getSchools(it.accessToken, it.client, it.uid), emptyList()) {it} }
    }

    override fun getSchool(schoolId: Int): Either<Failure, SchoolEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(schoolApiService.getSchool(it.accessToken, it.client, it.uid, schoolId), SchoolEntity.empty()) {it} }
    }

    override fun removeSchool(schoolId: Int): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { request.make(schoolApiService.deleteSchool(it.accessToken, it.client, it.uid, schoolId), Any()) {None()} }
    }

    override fun updateSchool(schoolEntity: SchoolEntity): Either<Failure, SchoolEntity> {
        return accountCache.getAccount()
            .flatMap { request.make(schoolApiService.updateSchool(it.accessToken, it.client, it.uid, schoolEntity.id, SchoolParams(schoolEntity.name, schoolEntity.cityId)), SchoolEntity.empty() ) {it} }

    }
}