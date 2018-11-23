package merryweather.com.tech45.di.component

import android.content.Context

import javax.inject.Singleton

import dagger.Component
import merryweather.com.tech45.App
import merryweather.com.tech45.api.TechService
import merryweather.com.tech45.di.modules.AppModule
import merryweather.com.tech45.di.modules.RepositoryModule
import merryweather.com.tech45.di.modules.RetrofitModule
import merryweather.com.tech45.di.modules.TechModule
import merryweather.com.tech45.di.qualifier.AppContext

/**
 * Created by S on 19.04.2018.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, TechModule::class, RetrofitModule::class, RepositoryModule::class))
interface AppComponent {

    @AppContext
    fun context(): Context

    fun ltechService(): TechService


    fun inject(theApp: App)
}
