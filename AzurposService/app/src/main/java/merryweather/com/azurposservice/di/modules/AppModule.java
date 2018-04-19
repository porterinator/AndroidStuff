package merryweather.com.azurposservice.di.modules;

import android.app.Application;
import android.content.Context;


import dagger.Module;
import dagger.Provides;
import merryweather.com.azurposservice.di.qualifier.AppContext;

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