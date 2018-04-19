package merryweather.com.azurposservice.di.modules;

import android.app.Activity;
import android.content.Context;


import dagger.Module;
import dagger.Provides;
import merryweather.com.azurposservice.di.scopes.PerActivity;


@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Context provideContext() {
        return activity;
    }

}