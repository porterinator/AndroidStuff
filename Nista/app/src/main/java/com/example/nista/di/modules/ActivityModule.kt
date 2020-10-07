package com.leroymerlin.cds.di.modules

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides
import com.leroymerlin.cds.di.scopes.PerActivity

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideContext(): Context {
        return activity
    }

}