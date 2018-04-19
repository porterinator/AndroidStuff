package merryweather.com.azurposservice.api;

import io.reactivex.Single;
import merryweather.com.azurposservice.model.Items;

/**
 * Created by S on 03.04.2018.
 */

public class NsodeService {

    private NSodeApi mApi;

    public NsodeService(NSodeApi mApi) {

        this.mApi = mApi;
    }

    public Single<Items> getItems(long id) {
        return mApi.getIems();
    }
}
