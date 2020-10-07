package com.leroymerlin.cds.di.modules

import android.content.Context
import com.example.nista.api.MovieApi
import com.example.nista.api.MovieService
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieModule {
    @Provides
    @Singleton
    fun provideMovieService(movieApi : MovieApi): MovieService {
        return MovieService(movieApi)
    }

    @Provides
    @Singleton
    fun provideMovieRetorfit(retrofit : Retrofit) : MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}
