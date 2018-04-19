package merryweather.com.shpock.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.shpock.api.NSodeApi;
import merryweather.com.shpock.api.NsodeService;

/**
 * Created by S on 19.04.2018.
 */

@Module(includes = ApiModule.class)
public class NsodeModule {
    @Provides
    @Singleton
    public NsodeService provideNSodeService(NSodeApi api) {
        return new NsodeService(api);
    }
}
