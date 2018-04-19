package merryweather.com.azurposservice.api;

import io.reactivex.Single;
import merryweather.com.azurposservice.model.Items;
import merryweather.com.azurposservice.service.Post;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by S on 03.04.2018.
 */

public interface NSodeApi {

    @GET("items/")
    Single<Items> getIems();
}
