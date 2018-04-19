package merryweather.com.shpock.api;

import io.reactivex.Single;
import merryweather.com.shpock.model.Items;
import retrofit2.http.GET;

/**
 * Created by S on 19.04.2018.
 */

public interface NSodeApi {

    @GET("items")
    Single<Items> getIems();
}
