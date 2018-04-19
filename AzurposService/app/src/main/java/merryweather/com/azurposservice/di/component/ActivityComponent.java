package merryweather.com.azurposservice.di.component;


import dagger.Component;
import merryweather.com.azurposservice.api.NsodeService;
import merryweather.com.azurposservice.di.modules.ActivityModule;
import merryweather.com.azurposservice.di.scopes.PerActivity;

/**
 * Created by S on 21.11.2017.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    NsodeService jsonPlaceHolderServie();
}
