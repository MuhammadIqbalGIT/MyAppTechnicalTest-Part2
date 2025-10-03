package com.example.myapptechnicaltest_part2

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application(){

    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}