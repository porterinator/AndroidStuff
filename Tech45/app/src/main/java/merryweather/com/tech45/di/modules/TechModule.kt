package merryweather.com.tech45.di.modules

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import merryweather.com.tech45.api.TechApi
import merryweather.com.tech45.api.TechService

/**
 * Created by S on 19.04.2018.
 */

@Module(includes = arrayOf(ApiModule::class))
class TechModule {
    @Provides
    @Singleton
    fun provideNSodeService(api: TechApi): TechService {
        return TechService(api)
    }
}
