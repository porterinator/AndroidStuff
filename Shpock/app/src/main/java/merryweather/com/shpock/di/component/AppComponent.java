package merryweather.com.shpock.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import merryweather.com.shpock.App;
import merryweather.com.shpock.api.NsodeService;
import merryweather.com.shpock.di.modules.AppModule;
import merryweather.com.shpock.di.modules.NsodeModule;
import merryweather.com.shpock.di.modules.RetrofitModule;
import merryweather.com.shpock.di.qualifier.AppContext;

/**
 * Created by S on 19.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, NsodeModule.class, RetrofitModule.class})
public interface AppComponent {

    @AppContext
    Context context();

    NsodeService nsodeService();

    void inject(final App theApp);
}
