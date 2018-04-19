package merryweather.com.shpock.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import merryweather.com.shpock.di.qualifier.AppContext;

/**
 * Created by S on 19.04.2018.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppContext
    Context providesContext() {
        return application.getApplicationContext();
    }

}