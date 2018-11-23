package merryweather.com.tech45.api

import io.reactivex.Single
import merryweather.com.tech45.model.Department
import merryweather.com.tech45.model.LoginResponse
import retrofit2.http.*

/**
 * Created by S on 17.05.2018.
 */

interface TechApi {

    @GET("Hello")
    fun login(@Query("login") login: String, @Query("password") password: String): Single<LoginResponse>

    @GET("GetAll")
    fun getAll(@Query("login") login: String, @Query("password") password: String): Single<Department>
}


/*@GET("phoneMask.php")
    Single<PhoneMask> getPhoneMask();

    @FormUrlEncoded
    @POST("auth.php")
    Single<LoginResult> auth(@Field("phone") String login, @Field("password") String password);

    @GET("./")
    Single<ArrayList<Entity>> getList();*/
