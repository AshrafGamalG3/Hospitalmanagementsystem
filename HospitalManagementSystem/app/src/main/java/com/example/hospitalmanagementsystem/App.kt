package com.example.hospitalmanagementsystem

import android.app.Application
import com.example.hospitalmanagementsystem.utils.MySharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)

    }

}