package com.leroymerlin.cds.di.di.modules

import android.content.Context
import com.example.nista.api.MovieApi
import com.example.nista.api.MovieService
import com.example.nista.repository.MovieRepository
import com.leroymerlin.cds.di.qualifier.AppContext

import javax.inject.Singleton

import dagger.Module
import dagger.Provides



@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(service : MovieService, @AppContext context : Context) : MovieRepository {
        return MovieRepository(service)
    }

}
