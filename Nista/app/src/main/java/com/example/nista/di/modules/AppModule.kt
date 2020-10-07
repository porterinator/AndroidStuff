package com.leroymerlin.cds.di.modules

import android.app.Application
import android.content.Context
import android.util.Log

import dagger.Module
import dagger.Provides
import com.leroymerlin.cds.di.qualifier.AppContext
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @AppContext
    internal fun providesContext(): Context {
        return application.applicationContext
    }


}