package merryweather.com.tech45.api

import io.reactivex.Single
import merryweather.com.tech45.model.Department
import merryweather.com.tech45.model.LoginResponse


/**
 * Created by S on 17.05.2018.
 */

class TechService(private val mApi: TechApi) {

    fun login(userName: String, password: String): Single<LoginResponse> {
        return mApi.login(userName, password)
    }

    fun getDepartments(userName: String, password: String): Single<Department> {
        return mApi.getAll(userName, password)
    }
}
