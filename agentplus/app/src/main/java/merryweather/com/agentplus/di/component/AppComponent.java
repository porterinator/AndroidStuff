package merryweather.com.agentplus.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import merryweather.com.agentplus.App;
import merryweather.com.agentplus.di.modules.AppModule;
import merryweather.com.agentplus.di.modules.RepositoryModule;
import merryweather.com.agentplus.di.qualifier.AppContext;
import merryweather.com.agentplus.repository.AgentplusRepository;

/**
 * Created by S on 19.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface AppComponent {

    @AppContext
    Context context();


    AgentplusRepository agentplusRepository();

    void inject(final App theApp);
}
