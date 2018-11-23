package merryweather.com.tech45.di.modules

import android.arch.lifecycle.ViewModel

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import merryweather.com.tech45.departments.DepartmentsViewModel
import merryweather.com.tech45.di.factory.ViewModelFactory
import merryweather.com.tech45.di.qualifier.ViewModelKey
import merryweather.com.tech45.login.LoginViewModel
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DepartmentsViewModel::class)
    internal abstract fun departmentsViewModel(departmentsViewModel: DepartmentsViewModel): ViewModel

}
