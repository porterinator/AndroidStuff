package merryweather.com.agentplus.di.component;

import dagger.Component;
import merryweather.com.agentplus.di.modules.ServiceModule;
import merryweather.com.agentplus.di.scopes.PerService;
import merryweather.com.agentplus.repository.AgentplusRepository;
import merryweather.com.agentplus.service.AgentplusService;

@PerService
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


    AgentplusRepository agentplusRepository();

    void inject(AgentplusService service);

}
