package merryweather.com.azurposservice.di;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


import java.util.HashMap;
import java.util.Map;

import merryweather.com.azurposservice.App;
import merryweather.com.azurposservice.di.component.ActivityComponent;
import merryweather.com.azurposservice.di.component.AppComponent;
import merryweather.com.azurposservice.di.component.DaggerActivityComponent;
import merryweather.com.azurposservice.di.component.DaggerAppComponent;
import merryweather.com.azurposservice.di.component.DaggerServiceComponent;
import merryweather.com.azurposservice.di.component.ServiceComponent;
import merryweather.com.azurposservice.di.modules.ActivityModule;
import merryweather.com.azurposservice.di.modules.AppModule;
import merryweather.com.azurposservice.di.modules.ServiceModule;


public final class ComponentManager {

    private AppComponent appComponent;

    private final Map<String, ActivityComponent> activityComponentMap = new HashMap<>();


    private ComponentManager() {
    }

    @NonNull
    public static ComponentManager getInstance() {
        return Holder.INSTANCE;
    }

    public final void init(final Application application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();

        appComponent.inject((App) application);
    }

    /**
     * For Services, Broadcasts, etc.
     */
    public final AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * For Activities
     */
    public final ActivityComponent getActivityComponent(final Activity activity) {
        final String key = getKey(activity);
        ActivityComponent activityComponent = activityComponentMap.get(key);
        if (activityComponent == null) {
            activityComponent = createNewActivityComponent(activity);
            activityComponentMap.put(key, activityComponent);
        }
        return activityComponent;
    }

    public final void removeActivityComponent(final Activity activity) {
        activityComponentMap.remove(getKey(activity));
    }


    /**
     * For Services
     */
    public final ServiceComponent getServiceComponent(final Service service) {
        return DaggerServiceComponent
                .builder()
                .serviceModule(new ServiceModule(service))
                .appComponent(appComponent)
                .build();
    }

    private ActivityComponent createNewActivityComponent(final Activity activity) {
        return DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(activity))
                .build();
    }

    @NonNull
    private String getKey(final Activity activity) {
        return activity.getLocalClassName();
    }

    private static final class Holder {
        private static final ComponentManager INSTANCE = new ComponentManager();
    }

}