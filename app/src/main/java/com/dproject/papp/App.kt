package com.dproject.papp

import android.app.Application
import com.dproject.papp.presentation.injection.AppModule
import com.dproject.papp.presentation.injection.DataModule
import com.dproject.papp.presentation.injection.ViewModelModule
import com.dproject.papp.presentation.ui.*
import com.dproject.papp.presentation.ui.core.RouteActivity
import dagger.Component
import javax.inject.Singleton


@Suppress("DEPRECATION")
class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(registerFragment: RegisterFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(changeCityFragment: ChangeCityFragment)
    fun inject(citiesFragment: CitiesFragment)
    fun inject(createCityFragment: CreateCityFragment)
    fun inject(schoolsFragment: SchoolsFragment)
    fun inject(schoolAddFragment: SchoolAddFragment)
    fun inject(schoolChangeFragment: SchoolChangeFragment)
    fun inject(studentGroupFragment: StudentGroupFragment)
    fun inject(studentGroupAddFragment: StudentGroupAddFragment)
    fun inject(studentGroupChangeFragment: StudentGroupChangeFragment)
    fun inject(studentFragment: StudentFragment)
    fun inject(studentAddFragment: StudentAddFragment)
    fun inject(studentChangeFragment: StudentChangeFragment)
    fun inject(personalValueGroupFragment: PersonalValueGroupFragment)
    fun inject(characteristicFragment: CharacteristicFragment)
    fun inject(characteristicAddFragment: CharacteristicAddFragment)
    fun inject(characteristicChangeFragment: CharacteristicChangeFragment)


    fun inject(routeActivity: RouteActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(changeCityActivity: ChangeCityActivity)
    fun inject(createCityActivity: CreateCityActivity)
    fun inject(schoolChangeActivity: SchoolChangeActivity)
    fun inject(studentGroupChangeActivity: StudentGroupChangeActivity)
    fun inject(studentChangeActivity: StudentChangeActivity)
    fun inject(characteristicChangeActivity: CharacteristicChangeActivity)
}