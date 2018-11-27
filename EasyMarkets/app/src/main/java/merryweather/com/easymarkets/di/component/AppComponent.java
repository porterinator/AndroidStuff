package merryweather.com.easymarkets.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import merryweather.com.easymarkets.App;
import merryweather.com.easymarkets.di.modules.AppModule;
import merryweather.com.easymarkets.di.modules.RepositoryModule;
import merryweather.com.easymarkets.di.qualifier.AppContext;
import merryweather.com.easymarkets.repository.AppartmentRepository;

/**
 * Created by S on 19.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface AppComponent {

    @AppContext
    Context context();


    AppartmentRepository appartmentRepository();

    void inject(final App theApp);
}
