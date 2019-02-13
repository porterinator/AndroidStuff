package merryweather.com.agentplus.di.modules;

import android.app.Service;


import dagger.Module;
import dagger.Provides;
import merryweather.com.agentplus.di.scopes.PerService;

@Module
public final class ServiceModule {

    private final Service service;

    public ServiceModule(final Service service) {
        this.service = service;
    }

    @PerService
    @Provides
    final Service provideService() {
        return service;
    }

}
