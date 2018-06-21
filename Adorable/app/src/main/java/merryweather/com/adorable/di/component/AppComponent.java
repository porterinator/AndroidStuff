package merryweather.com.adorable.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import merryweather.com.adorable.App;
import merryweather.com.adorable.api.OpenWeatherMapService;
import merryweather.com.adorable.di.modules.AppModule;
import merryweather.com.adorable.di.modules.PopViewersModule;
import merryweather.com.adorable.di.modules.RepositoryModule;
import merryweather.com.adorable.di.modules.RetrofitModule;
import merryweather.com.adorable.di.qualifier.AppContext;

/**
 * Created by S on 19.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, PopViewersModule.class, RetrofitModule.class, RepositoryModule.class})
public interface AppComponent {

    @AppContext
    Context context();

    OpenWeatherMapService openWeatherMapService();

    void inject(final App theApp);
}
