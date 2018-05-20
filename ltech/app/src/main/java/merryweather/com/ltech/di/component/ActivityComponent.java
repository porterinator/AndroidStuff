package merryweather.com.ltech.di.component;


import android.content.Context;

import dagger.Component;
import merryweather.com.ltech.auth.LoginActivity;
import merryweather.com.ltech.api.LTechService;
import merryweather.com.ltech.detail.EntityDetailActivity;
import merryweather.com.ltech.di.modules.ActivityModule;
import merryweather.com.ltech.di.scopes.PerActivity;
import merryweather.com.ltech.list.ListActivity;
import merryweather.com.ltech.repository.EntityRepository;

/**
 * Created by S on 17.05.2018.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();

    LTechService ltechService();

    EntityRepository entityRepository();

    void inject(LoginActivity activity);

    void inject(ListActivity activity);

    void inject(EntityDetailActivity activity);

}
