package com.dproject.papp.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dproject.papp.presentation.viewModel.*
import com.dproject.papp.presentation.viewModel.core.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel::class)
    abstract fun bindCityViewModel(cityViewModel: CityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SchoolViewModel::class)
    abstract fun bindSchoolViewModel(schoolViewModel: SchoolViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StudentGroupViewModel::class)
    abstract fun bindStudentGroupViewModel(studentGroupViewModel: StudentGroupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StudentViewModel::class)
    abstract fun bindStudentViewModel(studentViewModel: StudentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonalValueGroupViewModel::class)
    abstract fun bindPersonalValueGroupViewModel(personalValueGroupViewModel: PersonalValueGroupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacteristicViewModel::class)
    abstract fun bindCharacteristicViewModel(characteristicViewModel: CharacteristicViewModel): ViewModel
}