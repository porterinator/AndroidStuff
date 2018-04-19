package merryweather.com.shpock.di.component;


import android.content.Context;

import dagger.Component;
import merryweather.com.shpock.MainActivity;
import merryweather.com.shpock.api.NsodeService;
import merryweather.com.shpock.di.modules.ActivityModule;
import merryweather.com.shpock.di.scopes.PerActivity;

/**
 * Created by S on 19.04.2018.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();

    NsodeService nsodeService();

    void inject(MainActivity activity);

}
