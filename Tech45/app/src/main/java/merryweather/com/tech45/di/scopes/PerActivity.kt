package merryweather.com.tech45.di.scopes

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Scope

/**
 * Created by S on 17.05.2018.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PerActivity