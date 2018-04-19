package merryweather.com.azurposservice;

import android.app.Application;
import android.content.Intent;

import merryweather.com.azurposservice.di.ComponentManager;
import merryweather.com.azurposservice.di.component.AppComponent;
import merryweather.com.azurposservice.di.component.DaggerAppComponent;
import merryweather.com.azurposservice.di.modules.AppModule;
import merryweather.com.azurposservice.service.AzurposService;

/**
 * Created by S on 03.04.2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().init(this);
        startService(new Intent(this, AzurposService.class));
    }
}
