package merryweather.com.adorable.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import merryweather.com.adorable.api.OpenWeatherMapApi;
import merryweather.com.adorable.api.OpenWeatherMapService;

/**
 * Created by S on 19.04.2018.
 */

@Module(includes = ApiModule.class)
public class PopViewersModule {
    @Provides
    @Singleton
    public OpenWeatherMapService providePopViewersService(OpenWeatherMapApi api) {
        return new OpenWeatherMapService(api);
    }
}
