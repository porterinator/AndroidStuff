package merryweather.com.tech45.di.modules

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides
import merryweather.com.tech45.di.scopes.PerActivity

/**
 * Created by S on 19.04.2018.
 */

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideContext(): Context {
        return activity
    }

}