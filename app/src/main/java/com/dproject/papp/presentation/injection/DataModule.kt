package com.dproject.papp.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import com.dproject.papp.data.cache.AccountCache
import com.dproject.papp.data.cache.AccountCacheImpl
import com.dproject.papp.data.cache.SharedPrefsManager
import com.dproject.papp.data.remote.service.ServiceFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAccountCache(prefsManager: SharedPrefsManager): AccountCache = AccountCacheImpl(prefsManager)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = ServiceFactory.makeRetrofit(false)
}