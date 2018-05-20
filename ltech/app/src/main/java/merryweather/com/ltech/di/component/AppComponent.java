package merryweather.com.ltech.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import merryweather.com.ltech.App;
import merryweather.com.ltech.api.LTechService;
import merryweather.com.ltech.di.modules.AppModule;
import merryweather.com.ltech.di.modules.LtechModule;
import merryweather.com.ltech.di.modules.RepositoryModule;
import merryweather.com.ltech.di.modules.RetrofitModule;
import merryweather.com.ltech.di.qualifier.AppContext;
import merryweather.com.ltech.repository.EntityRepository;

/**
 * Created by S on 19.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, LtechModule.class, RetrofitModule.class, RepositoryModule.class})
public interface AppComponent {

    @AppContext
    Context context();

    LTechService ltechService();

    EntityRepository entityRepository();

    void inject(final App theApp);
}
