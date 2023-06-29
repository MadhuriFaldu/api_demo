package com.practicaltask

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

lateinit var instanceApp: PracticalApp

@HiltAndroidApp
class PracticalApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instanceApp = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}