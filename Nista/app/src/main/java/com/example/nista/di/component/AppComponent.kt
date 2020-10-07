package com.example.nista.di.component

import android.content.Context
import com.example.nista.App
import com.example.nista.api.MovieService
import com.example.nista.repository.MovieRepository
import com.leroymerlin.cds.di.di.modules.RepositoryModule
import com.leroymerlin.cds.di.modules.AppModule
import com.leroymerlin.cds.di.modules.MovieModule
import com.leroymerlin.cds.di.modules.RetrofitModule
import com.leroymerlin.cds.di.qualifier.AppContext
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, MovieModule::class, RepositoryModule::class, RetrofitModule::class))
interface AppComponent {

    @AppContext
    fun context(): Context

    fun movieService(): MovieService

    fun movieRepository(): MovieRepository


    fun inject(receiver : App)
}
