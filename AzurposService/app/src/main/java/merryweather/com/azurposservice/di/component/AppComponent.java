package merryweather.com.azurposservice.di.component;

import javax.inject.Singleton;

import dagger.Component;
import merryweather.com.azurposservice.App;
import merryweather.com.azurposservice.api.NsodeService;
import merryweather.com.azurposservice.di.modules.AppModule;
import merryweather.com.azurposservice.di.modules.RetrofitModule;
import merryweather.com.azurposservice.di.modules.JsonPlaceholderModule;
import merryweather.com.azurposservice.di.modules.ServiceModule;

/**
 * Created by S on 21.11.2017.
 */

@Singleton
@Component(modules = {AppModule.class, JsonPlaceholderModule.class, RetrofitModule.class, ServiceModule.class})
public interface AppComponent {

    NsodeService jsonPlaceHolderServie();

    void inject(final App theApp);
}
