package merryweather.com.tech45.di.component


import android.content.Context

import dagger.BindsInstance
import dagger.Component
import merryweather.com.tech45.departments.DepartmentsActivity
import merryweather.com.tech45.di.factory.ViewModelFactory
import merryweather.com.tech45.di.modules.ViewModelModule
import merryweather.com.tech45.login.LoginActivity
import merryweather.com.tech45.api.TechService
import merryweather.com.tech45.di.modules.ActivityModule
import merryweather.com.tech45.di.scopes.PerActivity


/**
 * Created by S on 17.05.2018.
 */

@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class, ViewModelModule::class))
interface ActivityComponent {

    fun context(): Context

    fun techService(): TechService

    fun inject(activity: LoginActivity)

    fun inject(activity: DepartmentsActivity)
}
