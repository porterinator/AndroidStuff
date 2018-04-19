package merryweather.com.azurposservice.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.azurposservice.api.NSodeApi;
import merryweather.com.azurposservice.api.NsodeService;

/**
 * Created by S on 21.11.2017.
 */

@Module(includes = ApiModule.class)
public class JsonPlaceholderModule {
    @Provides
    @Singleton
    public NsodeService provideJsonPlaceholderService(NSodeApi api) {
        return new NsodeService(api);
    }
}
