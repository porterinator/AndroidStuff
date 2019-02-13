package merryweather.com.agentplus.di.component;


import android.content.Context;

import dagger.Component;
import merryweather.com.agentplus.MainActivity;
import merryweather.com.agentplus.di.modules.ActivityModule;
import merryweather.com.agentplus.di.scopes.PerActivity;
import merryweather.com.agentplus.repository.AgentplusRepository;

/**
 * Created by S on 17.05.2018.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();


    AgentplusRepository agentplusRepository();

    void inject(MainActivity activity);

}
