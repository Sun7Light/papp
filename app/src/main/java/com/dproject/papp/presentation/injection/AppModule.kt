package com.dproject.papp.presentation.injection

import android.content.Context
import com.dproject.papp.data.*
import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.remote.Request
import com.dproject.papp.data.remote.service.*
import com.dproject.papp.domain.account.AccountRepository
import com.dproject.papp.domain.characteristic.CharacteristicRepository
import com.dproject.papp.domain.city.CityRepository
import com.dproject.papp.domain.personalValueGroup.PersonalValueGroupRepository
import com.dproject.papp.domain.school.SchoolRepository
import com.dproject.papp.domain.student.StudentRepository
import com.dproject.papp.domain.studentGroup.StudentGroupRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(accountApiService: AccountApiService, request: Request, accountCache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(accountApiService, request, accountCache)
    }

    @Provides
    @Singleton
    fun provideCityRepository(cityApiService: CityApiService, request: Request, accountCache: AccountCache): CityRepository {
        return CityRepositoryImpl(cityApiService, request, accountCache)
    }

    @Provides
    @Singleton
    fun provideSchoolRepository(schoolApiService: SchoolApiService, request: Request, accountCache: AccountCache): SchoolRepository {
        return SchoolRepositoryImpl(schoolApiService, request, accountCache)
    }

    @Provides
    @Singleton
    fun provideStudentGroupRepository(studentGroupApiService: StudentGroupApiService, request: Request, accountCache: AccountCache): StudentGroupRepository{
        return StudentGroupRepositoryImpl(studentGroupApiService, request, accountCache)
    }

    @Provides
    @Singleton
    fun provideStudentRepository(studentApiService: StudentApiService, request: Request, accountCache: AccountCache): StudentRepository {
        return StudentRepositoryImpl(studentApiService, request, accountCache)
    }

    @Provides
    @Singleton
    fun providePersonalValueGroupRepository(personalValueGroupApiService: PersonalValueGroupApiService, request: Request, accountCache: AccountCache): PersonalValueGroupRepository {
        return PersonalValueGroupRepositoryImpl(personalValueGroupApiService, request, accountCache)
    }

    @Provides
    @Singleton
    fun provideCharacteristicRepository(characteristicApiService: CharacteristicApiService, request: Request, accountCache: AccountCache): CharacteristicRepository {
        return CharacteristicRepositoryImpl(characteristicApiService, request, accountCache)
    }
}