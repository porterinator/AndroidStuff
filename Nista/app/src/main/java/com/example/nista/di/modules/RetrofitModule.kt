package com.leroymerlin.cds.di.modules

import android.content.Context

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leroymerlin.cds.di.qualifier.AppContext

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(APP_URL).build()
    }


    @Provides
    @Singleton
    fun provideRetrofitBuilder(converterFactory: Converter.Factory, client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())
                .addConverterFactory(converterFactory) // converterFactory
                .client(client)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@AppContext context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    companion object {
        const val APP_URL = "https://api.themoviedb.org/3/"
    }
}
