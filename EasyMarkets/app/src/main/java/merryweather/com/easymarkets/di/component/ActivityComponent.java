package merryweather.com.easymarkets.di.component;


import android.content.Context;

import dagger.Component;
import merryweather.com.easymarkets.MainActivity;
import merryweather.com.easymarkets.di.modules.ActivityModule;
import merryweather.com.easymarkets.di.scopes.PerActivity;
import merryweather.com.easymarkets.repository.AppartmentRepository;


/**
 * Created by S on 17.05.2018.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();

    AppartmentRepository appartmentRepository();

    void inject(MainActivity activity);


}
