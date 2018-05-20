package merryweather.com.ltech.api;

import java.util.ArrayList;

import io.reactivex.Single;
import merryweather.com.ltech.model.Entity;
import merryweather.com.ltech.model.LoginResult;
import merryweather.com.ltech.model.PhoneMask;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by S on 17.05.2018.
 */

public interface LTechApi {

    @GET("phoneMask.php")
    Single<PhoneMask> getPhoneMask();

    @FormUrlEncoded
    @POST("auth.php")
    Single<LoginResult> auth(@Field("phone") String login, @Field("password") String password);

    @GET("./")
    Single<ArrayList<Entity>> getList();

}
