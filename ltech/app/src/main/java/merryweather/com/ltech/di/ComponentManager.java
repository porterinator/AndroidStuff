package merryweather.com.ltech.di;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import merryweather.com.ltech.App;
import merryweather.com.ltech.di.component.ActivityComponent;
import merryweather.com.ltech.di.component.AppComponent;
import merryweather.com.ltech.di.component.DaggerActivityComponent;
import merryweather.com.ltech.di.component.DaggerAppComponent;
import merryweather.com.ltech.di.modules.ActivityModule;
import merryweather.com.ltech.di.modules.AppModule;


/**
 * Created by S on 19.04.2018.
 */

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