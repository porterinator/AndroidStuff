package merryweather.com.adorable.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import merryweather.com.adorable.di.qualifier.AppContext;

/**
 * Created by S on 17.05.2018.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @AppContext
    @Provides
    Context providesContext() {
        return application.getApplicationContext();
    }

}