@file:Suppress("DEPRECATION")

package com.dproject.papp.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    fun isConnected(): Boolean {
        return when(networkInfo()?.isConnected) {
            true -> true
            false, null -> false
        }
    }
    private fun networkInfo(): NetworkInfo? {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
    }
}