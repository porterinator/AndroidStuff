package merryweather.com.adorable.di.component;


import android.content.Context;

import dagger.Component;
import merryweather.com.adorable.MainActivity;
import merryweather.com.adorable.api.OpenWeatherMapService;
import merryweather.com.adorable.di.modules.ActivityModule;
import merryweather.com.adorable.di.scopes.PerActivity;

/**
 * Created by S on 17.05.2018.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();

    OpenWeatherMapService openWeatherMapService();

    void inject(MainActivity activity);

}
