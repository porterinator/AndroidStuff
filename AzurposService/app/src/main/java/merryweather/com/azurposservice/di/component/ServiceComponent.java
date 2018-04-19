package merryweather.com.azurposservice.di.component;


import dagger.Component;
import merryweather.com.azurposservice.api.NsodeService;
import merryweather.com.azurposservice.di.modules.ServiceModule;
import merryweather.com.azurposservice.di.scopes.PerService;
import merryweather.com.azurposservice.service.AzurposService;

/**
 * Created by S
 * on 15.11.2017.
 */

@PerService
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    NsodeService jsonPlaceHolderServie();

    void inject(AzurposService service);

}
