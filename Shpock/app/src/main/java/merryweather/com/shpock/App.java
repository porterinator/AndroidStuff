package merryweather.com.shpock;

import android.app.Application;

import merryweather.com.shpock.di.ComponentManager;

/**
 * Created by S on 19.04.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().init(this);
    }
}
