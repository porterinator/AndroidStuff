package com.example.nista

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.fragment.app.Fragment
import com.example.nista.di.component.*
import com.example.nista.di.modules.FragmentModule

import java.util.HashMap

import com.leroymerlin.cds.di.modules.ActivityModule
import com.leroymerlin.cds.di.modules.AppModule


class ComponentManager private constructor() {

    /**
     * For Services, Broadcasts, etc.
     */
    lateinit var appComponent: AppComponent
        private set

    private val activityComponentMap = HashMap<String, ActivityComponent>()

    private val fragmentComponentMap = HashMap<String, FragmentComponent>()

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .build()

        appComponent.inject(application as App)
    }

    /**
     * For Activities
     */
    fun getActivityComponent(activity: Activity): ActivityComponent {
        val key = getKey(activity)
        var activityComponent: ActivityComponent? = activityComponentMap[key]
//        if (activityComponent == null) {
        activityComponent = createNewActivityComponent(activity)
        activityComponentMap[key] = activityComponent
//        }
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

    /**
     * For Fragments
     */
    fun getFragmentComponent(fragment: Fragment): FragmentComponent {
        val activity = fragment.requireActivity()
        val args = fragment.arguments
        val key_suffix = if (args != null) "" + args.hashCode() else ""
        val key = getKey(activity) + key_suffix
        val activityComponent = getActivityComponent(activity)

//        if (fragmentComponent == null) {
        val fragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(activityComponent)
                .fragmentModule(FragmentModule(fragment))
                .build()
        fragmentComponentMap.put(key, fragmentComponent)
//        }
        return fragmentComponent

    }

    fun removeFragmentComponent(fragment: Fragment) {
        val activity = fragment.requireActivity()
        val args = fragment.arguments
        val key_suffix = if (args != null) "" + args.hashCode() else ""
        val key = getKey(activity) + key_suffix

        fragmentComponentMap.remove(key)
    }

    private fun getKey(activity: Activity): String {
        return activity.localClassName
    }

    private object Holder {
        val INSTANCE = ComponentManager()
    }

    companion object {

        val instance: ComponentManager by lazy { Holder.INSTANCE }
    }

}