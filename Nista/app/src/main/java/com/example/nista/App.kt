package com.example.nista

import android.app.Application
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        ComponentManager.instance.init(this)
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}