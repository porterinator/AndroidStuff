package merryweather.com.tech45

import android.app.Application

import merryweather.com.tech45.di.ComponentManager

/**
 * Created by S on 17.05.2018.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentManager.instance.init(this)
    }

}
