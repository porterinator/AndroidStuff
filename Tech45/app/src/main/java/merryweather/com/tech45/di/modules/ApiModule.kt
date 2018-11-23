package merryweather.com.tech45.di.modules


import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import merryweather.com.tech45.api.TechApi
import retrofit2.Retrofit

/**
 * Created by S on 17.05.2018.
 */

@Module(includes = arrayOf(RetrofitModule::class))
class ApiModule {
    @Provides
    @Singleton
    fun provideLTechApi(retrofit: Retrofit): TechApi {
        return retrofit.create(TechApi::class.java)
    }
}
