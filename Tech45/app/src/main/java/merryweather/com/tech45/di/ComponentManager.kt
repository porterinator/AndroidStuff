package merryweather.com.tech45.di

import android.app.Activity
import android.app.Application

import java.util.HashMap

import merryweather.com.tech45.App
import merryweather.com.tech45.di.component.ActivityComponent
import merryweather.com.tech45.di.component.AppComponent
import merryweather.com.tech45.di.component.DaggerActivityComponent
import merryweather.com.tech45.di.component.DaggerAppComponent
import merryweather.com.tech45.di.modules.ActivityModule
import merryweather.com.tech45.di.modules.AppModule


/**
 * Created by S on 19.04.2018.
 */

class ComponentManager private constructor() {

    /**
     * For Services, Broadcasts, etc.
     */
    var appComponent: AppComponent? = null
        private set

    private val activityComponentMap = HashMap<String, ActivityComponent>()

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()

        appComponent!!.inject(application as App)
    }

    /**
     * For Activities
     */
    fun getActivityComponent(activity: Activity): ActivityComponent {
        val key = getKey(activity)
        var activityComponent: ActivityComponent? = activityComponentMap[key]
        if (activityComponent == null) {
            activityComponent = createNewActivityComponent(activity)
            activityComponentMap[key] = activityComponent
        }
        return activityComponent
    }

    fun removeActivityComponent(activity: Activity) {
        activityComponentMap.remove(getKey(activity))
    }


    private fun createNewActivityComponent(activity: Activity): ActivityComponent {
        return DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(ActivityModule(activity))
                .build()
    }

    private fun getKey(activity: Activity): String {
        return activity.localClassName
    }

    private object Holder { val INSTANCE = ComponentManager() }

    companion object {

        val instance: ComponentManager by lazy { Holder.INSTANCE }
    }

}