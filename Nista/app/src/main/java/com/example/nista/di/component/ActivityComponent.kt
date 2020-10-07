package com.example.nista.di.component


import android.content.Context
import com.example.nista.list.MovieListActivity
import com.example.nista.api.MovieService
import com.example.nista.detail.MovieActivity
import com.example.nista.repository.MovieRepository
import com.leroymerlin.cds.di.modules.ActivityModule
import com.leroymerlin.cds.di.modules.ViewModelModule
import com.leroymerlin.cds.di.scopes.PerActivity
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, ViewModelModule::class))
interface ActivityComponent {

    fun context(): Context

    fun movieService(): MovieService

    fun movieRepository(): MovieRepository


    fun inject(activity: MovieListActivity)

    fun inject(activity: MovieActivity)

}
