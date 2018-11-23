package merryweather.com.tech45.di.modules

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides
import merryweather.com.tech45.di.qualifier.AppContext

/**
 * Created by S on 17.05.2018.
 */

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppContext
    internal fun providesContext(): Context {
        return application.applicationContext
    }

}