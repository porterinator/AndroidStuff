package merryweather.com.shpock.api;

import io.reactivex.Single;
import merryweather.com.shpock.model.Items;

/**
 * Created by S on 19.04.2018.
 */

public class NsodeService {

    private NSodeApi mApi;

    public NsodeService(NSodeApi mApi) {

        this.mApi = mApi;
    }

    public Single<Items> getItems() {
        return mApi.getIems();
    }
}
