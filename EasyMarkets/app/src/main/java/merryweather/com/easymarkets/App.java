package merryweather.com.easymarkets;

import android.app.Application;

import merryweather.com.easymarkets.di.ComponentManager;

/**
 * Created by S on 17.05.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().init(this);
    }

}
