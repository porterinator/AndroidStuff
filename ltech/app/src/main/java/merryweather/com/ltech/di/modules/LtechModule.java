package merryweather.com.ltech.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.ltech.api.LTechApi;
import merryweather.com.ltech.api.LTechService;

/**
 * Created by S on 19.04.2018.
 */

@Module(includes = ApiModule.class)
public class LtechModule {
    @Provides
    @Singleton
    public LTechService provideNSodeService(LTechApi api) {
        return new LTechService(api);
    }
}
